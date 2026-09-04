package com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity.ProduccionProductoPresentacionEntity;

@Repository
public interface ProduccionProductoPresentacionJpaRepository extends JpaRepository<ProduccionProductoPresentacionEntity, Long> {
    List<ProduccionProductoPresentacionEntity> findByIdProduccionProducto(Long idProduccionProducto);
}