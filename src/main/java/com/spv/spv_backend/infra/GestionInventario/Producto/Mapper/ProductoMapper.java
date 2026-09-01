package com.spv.spv_backend.infra.GestionInventario.Producto.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;
import com.spv.spv_backend.infra.GestionInventario.Producto.Entity.ProductoEntity;

@Component
public class ProductoMapper {

    public Producto toDomain(ProductoEntity entity) {
        if (entity == null) return null;

        Producto domain = new Producto();
        domain.setIdProducto(entity.getIdProducto());
        domain.setNombre(entity.getNombre());
        domain.setEstado(entity.getEstado());

        return domain;
    }

    public ProductoEntity toEntity(Producto domain) {
        if (domain == null) return null;

        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(domain.getIdProducto());
        entity.setNombre(domain.getNombre());
        entity.setEstado(domain.getEstado());

        return entity;
    }
}