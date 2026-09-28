package com.spv.spv_backend.domain.Seguridad.Usuario.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.Seguridad.Usuario.Model.Usuario;

public interface UsuarioRepositoryPort {
    List<Usuario> listActive();
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Usuario save(Usuario usuario);
}