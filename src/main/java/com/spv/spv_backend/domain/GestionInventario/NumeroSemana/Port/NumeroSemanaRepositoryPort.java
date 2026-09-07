package com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model.NumeroSemana;

public interface NumeroSemanaRepositoryPort {
    List<NumeroSemana> findAll();
    Optional<NumeroSemana> findById(Long id);
    NumeroSemana save(NumeroSemana numeroSemana);
    void delete(Long id);
    Integer findMaxNumeroSemanaByYear(int year);
}