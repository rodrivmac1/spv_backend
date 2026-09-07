package com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Entity.NumeroSemanaEntity;

@Repository
public interface NumeroSemanaJpaRepository extends JpaRepository<NumeroSemanaEntity, Long> {
    
    // Consulta para obtener el número de semana más alto del año correspondiente a la fecha de inicio
    @Query("SELECT MAX(e.numeroSemana) FROM NumeroSemanaEntity e WHERE EXTRACT(YEAR FROM e.fechaInicio) = :year")
    Optional<Integer> findMaxNumeroSemanaByYear(@Param("year") int year);
}