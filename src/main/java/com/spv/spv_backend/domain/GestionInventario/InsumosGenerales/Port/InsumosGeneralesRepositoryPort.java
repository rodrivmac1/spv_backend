package com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model.InsumosGenerales;

public interface InsumosGeneralesRepositoryPort {
    List<InsumosGenerales> listActive(); // Cambiado para listar solo activos
    Optional<InsumosGenerales> findById(Long id);
    InsumosGenerales save(InsumosGenerales insumosGenerales);
}