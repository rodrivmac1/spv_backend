package com.spv.spv_backend.application.GestionInventario.NumeroSemana;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model.InsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Port.InsumosGeneralesRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Port.MateriasPrimasRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model.NumeroSemana;
import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Port.NumeroSemanaRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;
import com.spv.spv_backend.domain.GestionInventario.Producto.Port.ProductoRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Port.ProductoInsumoRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model.ProduccionSemanalInsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Port.ProduccionSemanalInsumosGeneralesRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model.ProduccionSemanalProducto;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Port.ProduccionSemanalProductoRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaRequestDTO;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaResponseDTO;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.ProduccionSemanalInsumosGeneralesResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NumeroSemanaService {

    private final NumeroSemanaRepositoryPort repositoryPort;
    private final ProductoRepositoryPort productoRepositoryPort;
    private final MateriasPrimasRepositoryPort materiasPrimasRepositoryPort;
    private final ProductoInsumoRepositoryPort productoInsumoRepositoryPort;
    private final InsumosGeneralesRepositoryPort insumosGeneralesRepositoryPort;
    private final ProduccionSemanalProductoRepositoryPort produccionSemanalProductoRepositoryPort;
    private final ProduccionSemanalInsumosGeneralesRepositoryPort produccionSemanalInsumosGeneralesRepositoryPort; // <-- Nuevo puerto inyectado

    public List<NumeroSemanaResponseDTO> listarSemanas() {
        return repositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NumeroSemanaResponseDTO obtenerSemanaPorId(Long id) {
        NumeroSemana semana = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Semana de producción no encontrada con ID: " + id));
        return mapToResponse(semana);
    }

    @Transactional
    public NumeroSemanaResponseDTO crearSemana(NumeroSemanaRequestDTO request) {
        if (request == null || request.getFechaInicio() == null || request.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha inicio");
        }

        int year = request.getFechaInicio().getYear();

        Integer maxSemana = repositoryPort.findMaxNumeroSemanaByYear(year);
        int siguienteNumeroSemana = (maxSemana == null ? 0 : maxSemana) + 1;

        NumeroSemana semana = new NumeroSemana();
        semana.setFechaInicio(request.getFechaInicio());
        semana.setFechaFin(request.getFechaFin());
        semana.setNumeroSemana(siguienteNumeroSemana);

        NumeroSemana saved = repositoryPort.save(semana);
        if (saved == null || saved.getIdProduccionSemanal() == null) {
            throw new IllegalStateException("No fue posible obtener el ID de la semana creada");
        }

        // 1. Guardar automáticamente los insumos generales activos de la semana
        guardarInsumosGeneralesSemana(saved.getIdProduccionSemanal());

        // 2. Crear cálculos por producto
        crearCalculosPorProducto(saved.getIdProduccionSemanal());

        return mapToResponse(saved);
    }

    private void guardarInsumosGeneralesSemana(Long idProduccionSemanal) {
        List<InsumosGenerales> insumosActivos = insumosGeneralesRepositoryPort.listActive();
        if (insumosActivos.isEmpty()) {
            return;
        }

        List<ProduccionSemanalInsumosGenerales> insumosSemanal = insumosActivos.stream().map(insumo -> {
            ProduccionSemanalInsumosGenerales prodInsumo = new ProduccionSemanalInsumosGenerales();
            prodInsumo.setIdProduccionSemanal(idProduccionSemanal);
            prodInsumo.setIdInsumoGeneral(insumo.getIdInsumoGeneral());
            prodInsumo.setCosto(insumo.getCosto());
            return prodInsumo;
        }).collect(Collectors.toList());

        produccionSemanalInsumosGeneralesRepositoryPort.saveAll(insumosSemanal);
    }

    private void crearCalculosPorProducto(Long idProduccionSemanal) {
        List<Producto> productos = productoRepositoryPort.listActive();
        if (productos.isEmpty()) {
            return;
        }

        double totalKilos = 0;
        List<MateriasPrimas> materiasPrimas = productos.stream()
                .map(producto -> materiasPrimasRepositoryPort.findByIdProducto(producto.getIdProducto())
                        .orElseThrow(() -> new IllegalStateException(
                                "El producto con ID " + producto.getIdProducto() + " no tiene materia prima configurada")))
                .toList();

        for (MateriasPrimas materiaPrima : materiasPrimas) {
            validarMateriaPrima(materiaPrima);
            totalKilos += materiaPrima.getKgComprados();
        }

        if (totalKilos <= 0) {
            throw new IllegalStateException("El total de kilos comprados debe ser mayor que cero");
        }

        // Se obtienen los costos generales recién guardados para esta semana
        double costosGeneralesSemanales = produccionSemanalInsumosGeneralesRepositoryPort
                .findByIdProduccionSemanal(idProduccionSemanal).stream()
                .mapToDouble(ProduccionSemanalInsumosGenerales::getCosto)
                .sum();

        List<ProduccionSemanalProducto> calculos = new java.util.ArrayList<>(productos.size());
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            MateriasPrimas materiaPrima = materiasPrimas.get(i);

            double costoInsumos = productoInsumoRepositoryPort.findByIdProducto(producto.getIdProducto()).stream()
                    .mapToDouble(this::obtenerCosto)
                    .sum();
            double costoGeneralAsignado = costosGeneralesSemanales
                    * (materiaPrima.getKgComprados() / totalKilos);
            double costoTotalLote = costoInsumos + costoGeneralAsignado;

            double factorRendimiento = materiaPrima.getKgRendimiento() * 100;
            double gramosNetosFinales = materiaPrima.getKgComprados() * 1000
                    * (factorRendimiento / 100);
            double costo100g = (costoTotalLote / gramosNetosFinales) * 100;

            ProduccionSemanalProducto calculo = new ProduccionSemanalProducto();
            calculo.setIdProduccionSemanal(idProduccionSemanal);
            calculo.setIdProducto(producto.getIdProducto());
            calculo.setKgComprados(materiaPrima.getKgComprados());
            calculo.setCostoInsumos(costoInsumos);
            calculo.setCostoGeneralAsignado(costoGeneralAsignado);
            calculo.setCostoTotalLote(costoTotalLote);
            calculo.setKgRendimiento(materiaPrima.getKgRendimiento());
            calculo.setCosto100g(costo100g);
            calculos.add(calculo);
        }

        produccionSemanalProductoRepositoryPort.saveAll(calculos);
    }

    private void validarMateriaPrima(MateriasPrimas materiaPrima) {
        if (materiaPrima.getKgComprados() == null || materiaPrima.getKgComprados() <= 0) {
            throw new IllegalStateException("Los kilos comprados deben ser mayores que cero para el producto "
                    + materiaPrima.getIdProducto());
        }
        if (materiaPrima.getKgRendimiento() == null || materiaPrima.getKgRendimiento() <= 0) {
            throw new IllegalStateException("El rendimiento debe ser mayor que cero para el producto "
                    + materiaPrima.getIdProducto());
        }
    }

    private double obtenerCosto(ProductoInsumo insumo) {
        if (insumo.getCosto() == null || insumo.getCosto() < 0) {
            throw new IllegalStateException("El costo del insumo del producto no puede ser nulo ni negativo");
        }
        return insumo.getCosto();
    }

    public NumeroSemanaResponseDTO editarSemana(Long id, NumeroSemanaRequestDTO request) {
        if (request == null || request.getFechaInicio() == null || request.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha inicio");
        }

        NumeroSemana existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Semana de producción no encontrada con ID: " + id));

        existente.setFechaInicio(request.getFechaInicio());
        existente.setFechaFin(request.getFechaFin());

        NumeroSemana updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminarSemana(Long id) {
        if (!repositoryPort.findById(id).isPresent()) {
            throw new RuntimeException("Semana de producción no encontrada con ID: " + id);
        }
        repositoryPort.delete(id);
    }

    private NumeroSemanaResponseDTO mapToResponse(NumeroSemana dom) {
        NumeroSemanaResponseDTO res = new NumeroSemanaResponseDTO();
        res.setIdProduccionSemanal(dom.getIdProduccionSemanal());
        res.setFechaInicio(dom.getFechaInicio());
        res.setFechaFin(dom.getFechaFin());
        res.setNumeroSemana(dom.getNumeroSemana());

        // Obtener insumos generales de esta semana
        List<ProduccionSemanalInsumosGenerales> insumosGenerales = produccionSemanalInsumosGeneralesRepositoryPort
                .findByIdProduccionSemanal(dom.getIdProduccionSemanal());

        List<ProduccionSemanalInsumosGeneralesResponseDTO> insumosDTOs = insumosGenerales.stream().map(i -> {
            ProduccionSemanalInsumosGeneralesResponseDTO dto = new ProduccionSemanalInsumosGeneralesResponseDTO();
            dto.setIdProduccionSemanalInsumoGeneral(i.getIdProduccionSemanalInsumoGeneral());
            dto.setIdInsumoGeneral(i.getIdInsumoGeneral());
            dto.setNombreInsumo(i.getNombreInsumo());
            dto.setCosto(i.getCosto());
            return dto;
        }).collect(Collectors.toList());

        // Calcular la suma total de los insumos generales de la semana
        double sumaTotal = insumosGenerales.stream()
                .mapToDouble(i -> i.getCosto() != null ? i.getCosto() : 0.0)
                .sum();

        res.setInsumosGenerales(insumosDTOs);
        res.setTotalInsumosGenerales(sumaTotal);

        return res;
    }
}