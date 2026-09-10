package com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model.ProductoPresentacion;
import com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Entity.ProductoPresentacionEntity;

@Component
public class ProductoPresentacionMapper {

    public ProductoPresentacion toDomain(ProductoPresentacionEntity entity) {
        if (entity == null) return null;

        ProductoPresentacion domain = new ProductoPresentacion();
        domain.setIdProductoPresentacion(entity.getIdProductoPresentacion());
        domain.setIdProducto(entity.getIdProducto());
        domain.setEstado(entity.getEstado());
        domain.setNombre(entity.getNombre());
        domain.setGramos(entity.getGramos());
        domain.setMargenGanancia(entity.getMargenGanancia());

        return domain;
    }

    public ProductoPresentacionEntity toEntity(ProductoPresentacion domain) {
        if (domain == null) return null;

        ProductoPresentacionEntity entity = new ProductoPresentacionEntity();
        entity.setIdProductoPresentacion(domain.getIdProductoPresentacion());
        entity.setIdProducto(domain.getIdProducto());
        entity.setEstado(domain.getEstado());
        entity.setNombre(domain.getNombre());
        entity.setGramos(domain.getGramos());
        entity.setMargenGanancia(domain.getMargenGanancia());

        return entity;
    }
}