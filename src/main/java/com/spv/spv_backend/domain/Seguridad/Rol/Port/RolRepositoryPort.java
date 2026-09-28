package com.spv.spv_backend.domain.Seguridad.Rol.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;

public interface RolRepositoryPort {
    List<Rol> listActive();
    Optional<Rol> findById(Long id);
    Rol save(Rol rol);
    List<Rol> findAll();
}