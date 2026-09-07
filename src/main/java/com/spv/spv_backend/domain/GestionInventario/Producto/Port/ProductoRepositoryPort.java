package com.spv.spv_backend.domain.GestionInventario.Producto.Port;

import java.util.List;
import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO;

public interface ProductoRepositoryPort {
    List<Producto> listActive();
    List<ProductoConDetallesResponseDTO> listActiveWithDetails();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
}