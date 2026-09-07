package com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Entity.MateriasPrimasEntity;

@Repository
public interface MateriasPrimasJpaRepository extends JpaRepository<MateriasPrimasEntity, Long> {
    Optional<MateriasPrimasEntity> findByIdProducto(Long idProducto);
}