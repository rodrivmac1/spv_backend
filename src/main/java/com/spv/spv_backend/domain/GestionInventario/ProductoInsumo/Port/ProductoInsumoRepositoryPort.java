package com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Port;

import java.util.List;
import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;

public interface ProductoInsumoRepositoryPort {
    List<ProductoInsumo> findByIdProducto(Long idProducto);
    Optional<ProductoInsumo> findById(Long id);
    Optional<ProductoInsumo> findByIdAndIdProducto(Long idInsumo, Long idProducto);

    ProductoInsumo save(ProductoInsumo productoInsumo);
    void deleteById(Long id);
}