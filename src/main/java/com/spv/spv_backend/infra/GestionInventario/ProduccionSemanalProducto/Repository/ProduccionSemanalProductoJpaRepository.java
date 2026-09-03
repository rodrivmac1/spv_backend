package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity.ProduccionSemanalProductoEntity;

@Repository
public interface ProduccionSemanalProductoJpaRepository extends JpaRepository<ProduccionSemanalProductoEntity, Long> {
    List<ProduccionSemanalProductoEntity> findByIdProduccionSemanal(Long idProduccionSemanal);
}