package com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Entity.ProductoPresentacionEntity;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionGlobalResponseDTO;

@Repository
public interface ProductoPresentacionJpaRepository extends JpaRepository<ProductoPresentacionEntity, Long> {
    List<ProductoPresentacionEntity> findByIdProductoAndEstadoTrue(Long idProducto);
    Optional<ProductoPresentacionEntity> findByIdProductoPresentacionAndIdProductoAndEstadoTrue(
            Long idProductoPresentacion, Long idProducto);

    @Modifying
    @Query("UPDATE ProductoPresentacionEntity p SET p.estado = false WHERE p.idProducto = :idProducto")
    int desactivarPorProducto(@Param("idProducto") Long idProducto);

    @Query("SELECT new com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionGlobalResponseDTO(" +
           "p.idProducto, p.idProductoPresentacion, CONCAT(p.nombre, ' - ', p.gramos, 'g - ', prod.nombre)) " +
           "FROM ProductoPresentacionEntity p " +
           "JOIN ProductoEntity prod ON p.idProducto = prod.idProducto " +
           "WHERE p.estado = true AND prod.estado = true")
    List<ProductoPresentacionGlobalResponseDTO> findAllGlobalConDetalle();
}