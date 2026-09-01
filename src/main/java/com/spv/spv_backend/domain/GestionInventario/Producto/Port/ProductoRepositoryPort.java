package com.spv.spv_backend.domain.GestionInventario.Producto.Port;

import java.util.List;
import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;

public interface ProductoRepositoryPort {
    List<Producto> listActive();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
}