package com.spv.spv_backend.infra.GestionVentas.TipoCliente.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Model.TipoCliente;
import com.spv.spv_backend.infra.GestionVentas.TipoCliente.Entity.TipoClienteEntity;

@Component
public class TipoClienteMapper {

    public TipoCliente toDomain(TipoClienteEntity entity) {
        if (entity == null) return null;

        TipoCliente domain = new TipoCliente();
        domain.setIdTipoCliente(entity.getIdTipoCliente());
        domain.setNombre(entity.getNombre());
        domain.setEstado(entity.getEstado());

        return domain;
    }

    public TipoClienteEntity toEntity(TipoCliente domain) {
        if (domain == null) return null;

        TipoClienteEntity entity = new TipoClienteEntity();
        entity.setIdTipoCliente(domain.getIdTipoCliente());
        entity.setNombre(domain.getNombre());
        entity.setEstado(domain.getEstado());

        return entity;
    }
}