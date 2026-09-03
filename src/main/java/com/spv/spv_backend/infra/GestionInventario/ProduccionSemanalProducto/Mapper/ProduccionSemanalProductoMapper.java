package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model.ProduccionSemanalProducto;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity.ProduccionSemanalProductoEntity;

@Component
public class ProduccionSemanalProductoMapper {

    public ProduccionSemanalProducto toDomain(ProduccionSemanalProductoEntity entity) {
        if (entity == null) return null;

        ProduccionSemanalProducto domain = new ProduccionSemanalProducto();
        domain.setIdProduccionProducto(entity.getIdProduccionProducto());
        domain.setIdProduccionSemanal(entity.getIdProduccionSemanal());
        domain.setIdProducto(entity.getIdProducto());
        domain.setKgComprados(entity.getKgComprados());
        domain.setCostoInsumos(entity.getCostoInsumos());
        domain.setCostoGeneralAsignado(entity.getCostoGeneralAsignado());
        domain.setCostoTotalLote(entity.getCostoTotalLote());
        domain.setKgRendimiento(entity.getKgRendimiento());
        domain.setCosto100g(entity.getCosto100g());

        return domain;
    }

    public ProduccionSemanalProductoEntity toEntity(ProduccionSemanalProducto domain) {
        if (domain == null) return null;

        ProduccionSemanalProductoEntity entity = new ProduccionSemanalProductoEntity();
        entity.setIdProduccionProducto(domain.getIdProduccionProducto());
        entity.setIdProduccionSemanal(domain.getIdProduccionSemanal());
        entity.setIdProducto(domain.getIdProducto());
        entity.setKgComprados(domain.getKgComprados());
        entity.setCostoInsumos(domain.getCostoInsumos());
        entity.setCostoGeneralAsignado(domain.getCostoGeneralAsignado());
        entity.setCostoTotalLote(domain.getCostoTotalLote());
        entity.setKgRendimiento(domain.getKgRendimiento());
        entity.setCosto100g(domain.getCosto100g());

        return entity;
    }
}