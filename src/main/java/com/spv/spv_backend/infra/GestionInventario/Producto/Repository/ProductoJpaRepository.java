package com.spv.spv_backend.infra.GestionInventario.Producto.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.Producto.Entity.ProductoEntity;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findByEstadoTrue();
}