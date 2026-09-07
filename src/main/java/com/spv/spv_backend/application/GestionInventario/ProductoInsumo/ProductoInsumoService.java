package com.spv.spv_backend.application.GestionInventario.ProductoInsumo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Port.ProductoInsumoRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO.ProductoInsumoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO.ProductoInsumoResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoInsumoService {

    private final ProductoInsumoRepositoryPort repositoryPort;

    public List<ProductoInsumoResponseDTO> obtenerInsumosPorProducto(Long idProducto) {
        return repositoryPort.findByIdProducto(idProducto).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductoInsumoResponseDTO agregarInsumo(Long idProducto, ProductoInsumoRequestDTO request) {
        ProductoInsumo insumo = new ProductoInsumo();
        insumo.setIdProducto(idProducto);
        insumo.setNombre(request.getNombre());
        insumo.setCosto(request.getCosto());

        ProductoInsumo saved = repositoryPort.save(insumo);
        return mapToResponse(saved);
    }

    public ProductoInsumoResponseDTO editarInsumo(Long idProducto, Long idInsumo, ProductoInsumoRequestDTO request) {
        // Validación estricta: Busca el insumo validando que coincidan ambos IDs
        ProductoInsumo existente = repositoryPort.findByIdAndIdProducto(idInsumo, idProducto)
                .orElseThrow(() -> new RuntimeException("El insumo con ID " + idInsumo + " no pertenece al producto con ID " + idProducto));

        existente.setNombre(request.getNombre());
        existente.setCosto(request.getCosto());

        ProductoInsumo updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminarInsumo(Long idProducto, Long idInsumo) {
        // Validación estricta para eliminación
        ProductoInsumo existente = repositoryPort.findByIdAndIdProducto(idInsumo, idProducto)
                .orElseThrow(() -> new RuntimeException("El insumo con ID " + idInsumo + " no pertenece al producto con ID " + idProducto));
        
        repositoryPort.deleteById(existente.getIdProductoInsumo());
    }

    private ProductoInsumoResponseDTO mapToResponse(ProductoInsumo dom) {
        ProductoInsumoResponseDTO res = new ProductoInsumoResponseDTO();
        res.setIdProductoInsumo(dom.getIdProductoInsumo());
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setCosto(dom.getCosto());
        return res;
    }
}