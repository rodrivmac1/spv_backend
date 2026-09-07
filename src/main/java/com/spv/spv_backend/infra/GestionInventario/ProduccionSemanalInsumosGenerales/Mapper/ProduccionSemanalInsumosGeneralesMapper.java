package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model.ProduccionSemanalInsumosGenerales;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Entity.ProduccionSemanalInsumosGeneralesEntity;

@Component
public class ProduccionSemanalInsumosGeneralesMapper {

    public ProduccionSemanalInsumosGenerales toDomain(ProduccionSemanalInsumosGeneralesEntity entity) {
        if (entity == null) return null;

        ProduccionSemanalInsumosGenerales domain = new ProduccionSemanalInsumosGenerales();
        domain.setIdProduccionSemanalInsumoGeneral(entity.getIdProduccionSemanalInsumoGeneral());
        domain.setIdProduccionSemanal(entity.getIdProduccionSemanal());
        domain.setIdInsumoGeneral(entity.getIdInsumoGeneral());
        domain.setCosto(entity.getCosto());
        if (entity.getInsumoGeneral() != null) {
            domain.setNombreInsumo(entity.getInsumoGeneral().getNombre());
        }

        return domain;
    }

    public ProduccionSemanalInsumosGeneralesEntity toEntity(ProduccionSemanalInsumosGenerales domain) {
        if (domain == null) return null;

        ProduccionSemanalInsumosGeneralesEntity entity = new ProduccionSemanalInsumosGeneralesEntity();
        entity.setIdProduccionSemanalInsumoGeneral(domain.getIdProduccionSemanalInsumoGeneral());
        entity.setIdProduccionSemanal(domain.getIdProduccionSemanal());
        entity.setIdInsumoGeneral(domain.getIdInsumoGeneral());
        entity.setCosto(domain.getCosto());

        return entity;
    }
}