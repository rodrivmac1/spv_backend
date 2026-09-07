package com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Port;

import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;

public interface MateriasPrimasRepositoryPort {
    Optional<MateriasPrimas> findById(Long id);
    Optional<MateriasPrimas> findByIdProducto(Long idProducto);
    MateriasPrimas save(MateriasPrimas materiasPrimas);
}