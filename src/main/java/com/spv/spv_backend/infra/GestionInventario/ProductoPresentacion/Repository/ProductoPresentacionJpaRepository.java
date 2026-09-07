package com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Entity.ProductoPresentacionEntity;

@Repository
public interface ProductoPresentacionJpaRepository extends JpaRepository<ProductoPresentacionEntity, Long> {
    List<ProductoPresentacionEntity> findByIdProducto(Long idProducto);
    Optional<ProductoPresentacionEntity> findByIdProductoPresentacionAndIdProducto(Long idProductoPresentacion, Long idProducto);
}