package com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Entity.ProductoInsumoEntity;

@Repository
public interface ProductoInsumoJpaRepository extends JpaRepository<ProductoInsumoEntity, Long> {
    List<ProductoInsumoEntity> findByIdProducto(Long idProducto);

    Optional<ProductoInsumoEntity> findByIdProductoInsumoAndIdProducto(Long idProductoInsumo, Long idProducto);
}