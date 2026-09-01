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

    public List<ProductoResponseDTO> obtenerProductosActivos() {
        return productoRepositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToResponse(producto);
    }

    // ⭐ SERVICIO TRANSACCIONAL MAESTRO: Guarda el producto y todas sus tablas hijas en una sola operación
    @Transactional
    public ProductoResponseDTO crearProductoCompleto(ProductoCompletoRequestDTO request) {
        // 1. Guardar Producto Padre
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setEstado(true); // Nace activo
        Producto productoGuardado = productoRepositoryPort.save(producto);
        Long idProducto = productoGuardado.getIdProducto();

        // 2. Guardar Insumos Específicos (si vienen en el request)
        if (request.getInsumos() != null && !request.getInsumos().isEmpty()) {
            for (var insumoDto : request.getInsumos()) {
                ProductoInsumo insumo = new ProductoInsumo();
                insumo.setIdProducto(idProducto);
                insumo.setNombre(insumoDto.getNombre());
                insumo.setCosto(insumoDto.getCosto());
                insumoRepositoryPort.save(insumo);
            }
        }

        // 3. Guardar Materia Prima Base (si viene en el request)
        if (request.getMateriaPrima() != null) {
            var mpDto = request.getMateriaPrima();
            MateriasPrimas mp = new MateriasPrimas();
            mp.setIdProducto(idProducto);
            mp.setNombre(mpDto.getNombre());
            mp.setKgComprados(mpDto.getKgComprados());
            mp.setKgRendimiento(mpDto.getKgRendimiento());
            materiaPrimaRepositoryPort.save(mp);
        }

        // 4. Guardar Presentaciones (si vienen en el request)
        if (request.getPresentaciones() != null && !request.getPresentaciones().isEmpty()) {
            for (var presDto : request.getPresentaciones()) {
                ProductoPresentacion pres = new ProductoPresentacion();
                pres.setIdProducto(idProducto);
                pres.setNombre(presDto.getNombre());
                pres.setGramos(presDto.getGramos());
                pres.setMargenGanancia(presDto.getMargenGanancia());
                presentacionRepositoryPort.save(pres);
            }
        }

        return mapToResponse(productoGuardado);
    }

    // Editar solo el nombre del producto
    public ProductoResponseDTO editarProducto(Long id, ProductoRequestDTO request) {
        Producto existente = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        existente.setNombre(request.getNombre());
        Producto actualizado = productoRepositoryPort.save(existente);
        return mapToResponse(actualizado);
    }

    // Eliminación lógica del producto
    public void eliminacionLogica(Long id) {
        Producto existente = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        existente.setEstado(false);
        productoRepositoryPort.save(existente);
    }

    private ProductoResponseDTO mapToResponse(Producto dom) {
        ProductoResponseDTO res = new ProductoResponseDTO();
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setEstado(dom.getEstado());
        return res;
    }
}