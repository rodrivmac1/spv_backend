package com.spv.spv_backend.infra.Seguridad.Usuario.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.Seguridad.Usuario.Model.Usuario;
import com.spv.spv_backend.infra.Seguridad.Rol.Mapper.RolMapper;
import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;

@Component
public class UsuarioMapper {

    private final RolMapper rolMapper;

    public UsuarioMapper(RolMapper rolMapper) {
        this.rolMapper = rolMapper;
    }

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;

        Usuario domain = new Usuario();
        domain.setIdUsuario(entity.getIdUsuario());
        domain.setRol(rolMapper.toDomain(entity.getRol()));
        domain.setNombre(entity.getNombre());
        domain.setNombreUsuario(entity.getNombreUsuario());
        domain.setPasswordHash(entity.getPasswordHash());
        domain.setUltimoAcceso(entity.getUltimoAcceso());
        domain.setEstado(entity.getEstado());
        domain.setFechaCreacion(entity.getFechaCreacion());

        return domain;
    }

    public UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(domain.getIdUsuario());
        entity.setRol(rolMapper.toEntity(domain.getRol()));
        entity.setNombre(domain.getNombre());
        entity.setNombreUsuario(domain.getNombreUsuario());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setUltimoAcceso(domain.getUltimoAcceso());
        entity.setEstado(domain.getEstado());
        entity.setFechaCreacion(domain.getFechaCreacion());

        return entity;
    }
}