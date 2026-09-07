package com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity.ProduccionProductoPresentacionEntity;

@Repository
public interface ProduccionProductoPresentacionJpaRepository extends JpaRepository<ProduccionProductoPresentacionEntity, Long> {
    @Query("""
            SELECT p
            FROM ProduccionProductoPresentacionEntity p
            LEFT JOIN FETCH p.productoPresentacion
            WHERE p.idProduccionProducto = :idProduccionProducto
            """)
    List<ProduccionProductoPresentacionEntity> findByIdProduccionProducto(
            @Param("idProduccionProducto") Long idProduccionProducto);

    @Query("""
            SELECT p
            FROM ProduccionProductoPresentacionEntity p
            LEFT JOIN FETCH p.productoPresentacion
            WHERE p.idProduccionProducto = :idProduccionProducto
              AND p.produccionSemanalProducto.idProduccionSemanal = :idProduccionSemanal
            """)
    List<ProduccionProductoPresentacionEntity> findByIdProduccionProductoAndIdProduccionSemanal(
            @Param("idProduccionProducto") Long idProduccionProducto,
            @Param("idProduccionSemanal") Long idProduccionSemanal);
}