package com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;
import com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Entity.ProductoInsumoEntity;

@Component
public class ProductoInsumoMapper {

    public ProductoInsumo toDomain(ProductoInsumoEntity entity) {
        if (entity == null) return null;

        ProductoInsumo domain = new ProductoInsumo();
        domain.setIdProductoInsumo(entity.getIdProductoInsumo());
        domain.setIdProducto(entity.getIdProducto());
        domain.setNombre(entity.getNombre());
        domain.setCosto(entity.getCosto());

        return domain;
    }

    public ProductoInsumoEntity toEntity(ProductoInsumo domain) {
        if (domain == null) return null;

        ProductoInsumoEntity entity = new ProductoInsumoEntity();
        entity.setIdProductoInsumo(domain.getIdProductoInsumo());
        entity.setIdProducto(domain.getIdProducto());
        entity.setNombre(domain.getNombre());
        entity.setCosto(domain.getCosto());

        return entity;
    }
}