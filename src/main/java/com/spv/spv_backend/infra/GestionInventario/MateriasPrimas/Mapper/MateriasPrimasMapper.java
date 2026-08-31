package com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;
import com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Entity.MateriasPrimasEntity;

@Component
public class MateriasPrimasMapper {

    public MateriasPrimas toDomain(MateriasPrimasEntity entity) {
        if (entity == null) return null;

        MateriasPrimas domain = new MateriasPrimas();
        domain.setIdMateriaPrima(entity.getIdMateriaPrima());
        domain.setIdProducto(entity.getIdProducto());
        domain.setNombre(entity.getNombre());
        domain.setKgComprados(entity.getKgComprados());
        domain.setKgRendimiento(entity.getKgRendimiento());

        return domain;
    }

    public MateriasPrimasEntity toEntity(MateriasPrimas domain) {
        if (domain == null) return null;

        MateriasPrimasEntity entity = new MateriasPrimasEntity();
        entity.setIdMateriaPrima(domain.getIdMateriaPrima());
        entity.setIdProducto(domain.getIdProducto());
        entity.setNombre(domain.getNombre());
        entity.setKgComprados(domain.getKgComprados());
        entity.setKgRendimiento(domain.getKgRendimiento());

        return entity;
    }
}