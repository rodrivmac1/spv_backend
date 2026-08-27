package com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model.InsumosGenerales;
import com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Entity.InsumosGeneralesEntity;

@Component
public class InsumosGeneralesMapper {

    public InsumosGenerales toDomain(InsumosGeneralesEntity entity) {
        if (entity == null) return null;

        InsumosGenerales domain = new InsumosGenerales();
        domain.setIdInsumoGeneral(entity.getIdInsumoGeneral());
        domain.setNombre(entity.getNombre());
        domain.setCosto(entity.getCosto());
        domain.setFechaaAgregado(entity.getFechaaAgregado());
        domain.setEstado(entity.getEstado());

        return domain;
    }

    public InsumosGeneralesEntity toEntity(InsumosGenerales domain) {
        if (domain == null) return null;

        InsumosGeneralesEntity entity = new InsumosGeneralesEntity();
        entity.setIdInsumoGeneral(domain.getIdInsumoGeneral());
        entity.setNombre(domain.getNombre());
        entity.setCosto(domain.getCosto());
        entity.setFechaaAgregado(domain.getFechaaAgregado());
        entity.setEstado(domain.getEstado());

        return entity;
    }
}