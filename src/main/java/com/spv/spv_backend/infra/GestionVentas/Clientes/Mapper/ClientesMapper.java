package com.spv.spv_backend.infra.GestionVentas.Clientes.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.Clientes.Model.Clientes;
import com.spv.spv_backend.infra.GestionVentas.Clientes.Entity.ClientesEntity;

@Component
public class ClientesMapper {

    public Clientes toDomain(ClientesEntity entity) {
        if (entity == null) return null;

        Clientes domain = new Clientes();
        domain.setIdCliente(entity.getIdCliente());
        domain.setNombre(entity.getNombre());
        domain.setIdTipoCliente(entity.getIdTipoCliente());
        domain.setEstado(entity.getEstado());

        return domain;
    }

    public ClientesEntity toEntity(Clientes domain) {
        if (domain == null) return null;

        ClientesEntity entity = new ClientesEntity();
        entity.setIdCliente(domain.getIdCliente());
        entity.setNombre(domain.getNombre());
        entity.setIdTipoCliente(domain.getIdTipoCliente());
        entity.setEstado(domain.getEstado());

        return entity;
    }
}