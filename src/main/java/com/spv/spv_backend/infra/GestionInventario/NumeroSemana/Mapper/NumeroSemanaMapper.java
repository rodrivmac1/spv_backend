package com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model.NumeroSemana;
import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Entity.NumeroSemanaEntity;

@Component
public class NumeroSemanaMapper {

    public NumeroSemana toDomain(NumeroSemanaEntity entity) {
        if (entity == null) return null;

        NumeroSemana domain = new NumeroSemana();
        domain.setIdProduccionSemanal(entity.getIdProduccionSemanal());
        domain.setFechaInicio(entity.getFechaInicio());
        domain.setFechaFin(entity.getFechaFin());
        domain.setNumeroSemana(entity.getNumeroSemana());

        return domain;
    }

    public NumeroSemanaEntity toEntity(NumeroSemana domain) {
        if (domain == null) return null;

        NumeroSemanaEntity entity = new NumeroSemanaEntity();
        entity.setIdProduccionSemanal(domain.getIdProduccionSemanal());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setFechaFin(domain.getFechaFin());
        entity.setNumeroSemana(domain.getNumeroSemana());

        return entity;
    }
}