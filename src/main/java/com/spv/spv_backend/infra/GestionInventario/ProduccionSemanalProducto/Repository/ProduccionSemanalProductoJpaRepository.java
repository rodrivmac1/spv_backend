package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity.ProduccionSemanalProductoEntity;

@Repository
public interface ProduccionSemanalProductoJpaRepository extends JpaRepository<ProduccionSemanalProductoEntity, Long> {

    @Query("SELECT p FROM ProduccionSemanalProductoEntity p LEFT JOIN FETCH p.producto WHERE p.idProduccionSemanal = :idProduccionSemanal")
    List<ProduccionSemanalProductoEntity> findByIdProduccionSemanal(@Param("idProduccionSemanal") Long idProduccionSemanal);

    @Query("SELECT p FROM ProduccionSemanalProductoEntity p LEFT JOIN FETCH p.producto WHERE p.idProduccionProducto = :id")
    Optional<ProduccionSemanalProductoEntity> findById(@Param("id") Long id);
}