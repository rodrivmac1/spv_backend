package com.spv.spv_backend.application.GestionInventario.Producto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Port.MateriasPrimasRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;
import com.spv.spv_backend.domain.GestionInventario.Producto.Port.ProductoRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Port.ProductoInsumoRepositoryPort;
import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model.ProductoPresentacion;
import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Port.ProductoPresentacionRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoCompletoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final ProductoInsumoRepositoryPort insumoRepositoryPort;
    private final MateriasPrimasRepositoryPort materiaPrimaRepositoryPort;
    private final ProductoPresentacionRepositoryPort presentacionRepositoryPort;

    // Método modificado para devolver la lista con los nuevos campos agregados
    public List<ProductoConDetallesResponseDTO> obtenerProductosActivos() {
        return productoRepositoryPort.listActiveWithDetails();
    }

    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToResponse(producto);
    }

    @Transactional
    public ProductoResponseDTO crearProductoCompleto(ProductoCompletoRequestDTO request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setEstado(true);
        Producto productoGuardado = productoRepositoryPort.save(producto);
        Long idProducto = productoGuardado.getIdProducto();

        if (request.getInsumos() != null && !request.getInsumos().isEmpty()) {
            for (var insumoDto : request.getInsumos()) {
                ProductoInsumo insumo = new ProductoInsumo();
                insumo.setIdProducto(idProducto);
                insumo.setNombre(insumoDto.getNombre());
                insumo.setCosto(insumoDto.getCosto());
                insumoRepositoryPort.save(insumo);
            }
        }

        if (request.getMateriaPrima() != null) {
            var mpDto = request.getMateriaPrima();
            MateriasPrimas mp = new MateriasPrimas();
            mp.setIdProducto(idProducto);
            mp.setNombre(mpDto.getNombre());
            mp.setKgComprados(mpDto.getKgComprados());
            mp.setKgRendimiento(mpDto.getKgRendimiento());
            materiaPrimaRepositoryPort.save(mp);
        }

        if (request.getPresentaciones() != null && !request.getPresentaciones().isEmpty()) {
            for (var presDto : request.getPresentaciones()) {
                ProductoPresentacion pres = new ProductoPresentacion();
                pres.setIdProducto(idProducto);
                pres.setEstado(true);
                pres.setNombre(presDto.getNombre());
                pres.setGramos(presDto.getGramos());
                pres.setMargenGanancia(presDto.getMargenGanancia());
                presentacionRepositoryPort.save(pres);
            }
        }

        return mapToResponse(productoGuardado);
    }

    public ProductoResponseDTO editarProducto(Long id, ProductoRequestDTO request) {
        Producto existente = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        existente.setNombre(request.getNombre());
        Producto actualizado = productoRepositoryPort.save(existente);
        return mapToResponse(actualizado);
    }

    @Transactional
    public void eliminacionLogica(Long id) {
        Producto existente = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        existente.setEstado(false);
        productoRepositoryPort.save(existente);
        presentacionRepositoryPort.desactivarPorProducto(id);
    }

    private ProductoResponseDTO mapToResponse(Producto dom) {
        ProductoResponseDTO res = new ProductoResponseDTO();
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setEstado(dom.getEstado());
        res.setFechaCreacion(dom.getFechaCreacion());
        return res;
    }
}