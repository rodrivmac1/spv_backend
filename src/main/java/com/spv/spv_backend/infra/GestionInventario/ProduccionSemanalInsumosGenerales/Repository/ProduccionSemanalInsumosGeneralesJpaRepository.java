package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Entity.ProduccionSemanalInsumosGeneralesEntity;

@Repository
public interface ProduccionSemanalInsumosGeneralesJpaRepository extends JpaRepository<ProduccionSemanalInsumosGeneralesEntity, Long> {
    
    @Query("SELECT p FROM ProduccionSemanalInsumosGeneralesEntity p LEFT JOIN FETCH p.insumoGeneral WHERE p.idProduccionSemanal = :idProduccionSemanal")
    List<ProduccionSemanalInsumosGeneralesEntity> findByIdProduccionSemanal(@Param("idProduccionSemanal") Long idProduccionSemanal);
}