package com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Entity.InsumosGeneralesEntity;

@Repository
public interface InsumosGeneralesJpaRepository extends JpaRepository<InsumosGeneralesEntity, Long> {
    // Método para traer únicamente los insumos cuyo estado sea true (activo)
    List<InsumosGeneralesEntity> findByEstadoTrue();
}