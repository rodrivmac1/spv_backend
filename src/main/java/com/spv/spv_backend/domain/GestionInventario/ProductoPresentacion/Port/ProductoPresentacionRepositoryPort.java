package com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Port;

import java.util.List;
import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model.ProductoPresentacion;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionGlobalResponseDTO;

public interface ProductoPresentacionRepositoryPort {
    List<ProductoPresentacion> findByIdProducto(Long idProducto);
    Optional<ProductoPresentacion> findById(Long id);
    Optional<ProductoPresentacion> findByIdAndIdProducto(Long idPresentacion, Long idProducto);

    List<ProductoPresentacionGlobalResponseDTO> obtenerTodasGlobales();

    ProductoPresentacion save(ProductoPresentacion productoPresentacion);
    void deleteById(Long id);
}