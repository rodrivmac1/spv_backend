package com.spv.spv_backend.infra.Seguridad.Rol.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;
import com.spv.spv_backend.infra.Seguridad.Rol.Entity.RolEntity;

@Component
public class RolMapper {

    public Rol toDomain(RolEntity entity) {
        if (entity == null) return null;

        Rol domain = new Rol();
        domain.setIdRol(entity.getIdRol());
        domain.setNombre(entity.getNombre());
        domain.setDescripcion(entity.getDescripcion());
        domain.setEstado(entity.getEstado());

        return domain;
    }

    public RolEntity toEntity(Rol domain) {
        if (domain == null) return null;

        RolEntity entity = new RolEntity();
        entity.setIdRol(domain.getIdRol());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setEstado(domain.getEstado());

        return entity;
    }
}