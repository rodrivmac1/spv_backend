package com.spv.spv_backend.infra.Seguridad.Usuario.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.Seguridad.Usuario.Model.Usuario;
import com.spv.spv_backend.domain.Seguridad.Usuario.Port.UsuarioRepositoryPort;
import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;
import com.spv.spv_backend.infra.Seguridad.Usuario.Mapper.UsuarioMapper;
import com.spv.spv_backend.infra.Seguridad.Usuario.Repository.UsuarioJpaRepository;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioMapper mapper;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository, UsuarioMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Usuario> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return jpaRepository.findByNombreUsuario(nombreUsuario).map(mapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity savedEntity = jpaRepository.save(entity);
        return jpaRepository.findById(savedEntity.getIdUsuario())
                .map(mapper::toDomain)
                .orElseThrow(() -> new IllegalStateException(
                        "No se pudo recuperar el usuario guardado con ID: "
                                + savedEntity.getIdUsuario()));
    }
}