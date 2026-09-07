package com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Mapper;

import org.springframework.stereotype.Component;
import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Model.ProduccionProductoPresentacion;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity.ProduccionProductoPresentacionEntity;

@Component
public class ProduccionProductoPresentacionMapper {

    public ProduccionProductoPresentacion toDomain(ProduccionProductoPresentacionEntity entity) {
        if (entity == null) return null;
        ProduccionProductoPresentacion domain = new ProduccionProductoPresentacion();
        domain.setIdProduccionProductoPresentacion(entity.getIdProduccionProductoPresentacion());
        domain.setIdProduccionProducto(entity.getIdProduccionProducto());
        domain.setIdProductoPresentacion(entity.getIdProductoPresentacion());
        domain.setCostoProduccion(entity.getCostoProduccion());
        domain.setPrecioVentaSugerido(entity.getPrecioVentaSugerido());
        if (entity.getProductoPresentacion() != null) {
            domain.setNombre(entity.getProductoPresentacion().getNombre());
            domain.setGramos(entity.getProductoPresentacion().getGramos());
        }
        return domain;
    }

    public ProduccionProductoPresentacionEntity toEntity(ProduccionProductoPresentacion domain) {
        if (domain == null) return null;
        ProduccionProductoPresentacionEntity entity = new ProduccionProductoPresentacionEntity();
        entity.setIdProduccionProductoPresentacion(domain.getIdProduccionProductoPresentacion());
        entity.setIdProduccionProducto(domain.getIdProduccionProducto());
        entity.setIdProductoPresentacion(domain.getIdProductoPresentacion());
        entity.setCostoProduccion(domain.getCostoProduccion());
        entity.setPrecioVentaSugerido(domain.getPrecioVentaSugerido());
        return entity;
    }
}