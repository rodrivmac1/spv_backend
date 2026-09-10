package com.spv.spv_backend.application.GestionInventario.ProductoPresentacion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model.ProductoPresentacion;
import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Port.ProductoPresentacionRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionGlobalResponseDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoPresentacionService {

    private final ProductoPresentacionRepositoryPort repositoryPort;

    public List<ProductoPresentacionResponseDTO> obtenerPresentacionesPorProducto(Long idProducto) {
        return repositoryPort.findByIdProducto(idProducto).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductoPresentacionResponseDTO agregarPresentacion(Long idProducto, ProductoPresentacionRequestDTO request) {
        ProductoPresentacion presentacion = new ProductoPresentacion();
        presentacion.setIdProducto(idProducto);
        presentacion.setNombre(request.getNombre());
        presentacion.setGramos(request.getGramos());
        presentacion.setMargenGanancia(request.getMargenGanancia());

        ProductoPresentacion saved = repositoryPort.save(presentacion);
        return mapToResponse(saved);
    }

    public ProductoPresentacionResponseDTO editarPresentacion(Long idProducto, Long idPresentacion, ProductoPresentacionRequestDTO request) {
        // Validación estricta con ambos IDs
        ProductoPresentacion existente = repositoryPort.findByIdAndIdProducto(idPresentacion, idProducto)
                .orElseThrow(() -> new RuntimeException("La presentación con ID " + idPresentacion + " no pertenece al producto con ID " + idProducto));

        existente.setNombre(request.getNombre());
        existente.setGramos(request.getGramos());
        existente.setMargenGanancia(request.getMargenGanancia());

        ProductoPresentacion updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminarPresentacion(Long idProducto, Long idPresentacion) {
        // Validación estricta para eliminación
        ProductoPresentacion existente = repositoryPort.findByIdAndIdProducto(idPresentacion, idProducto)
                .orElseThrow(() -> new RuntimeException("La presentación con ID " + idPresentacion + " no pertenece al producto con ID " + idProducto));
        
        repositoryPort.deleteById(existente.getIdProductoPresentacion());
    }
    
    private ProductoPresentacionResponseDTO mapToResponse(ProductoPresentacion dom) {
        ProductoPresentacionResponseDTO res = new ProductoPresentacionResponseDTO();
        res.setIdProductoPresentacion(dom.getIdProductoPresentacion());
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setGramos(dom.getGramos());
        res.setMargenGanancia(dom.getMargenGanancia());
        return res;
    }
    public List<ProductoPresentacionGlobalResponseDTO> obtenerTodasLasPresentacionesGlobales() {
        return repositoryPort.obtenerTodasGlobales();
    }
}