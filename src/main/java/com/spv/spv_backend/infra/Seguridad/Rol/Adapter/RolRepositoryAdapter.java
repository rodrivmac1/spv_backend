package com.spv.spv_backend.infra.Seguridad.Rol.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;
import com.spv.spv_backend.domain.Seguridad.Rol.Port.RolRepositoryPort;
import com.spv.spv_backend.infra.Seguridad.Rol.Entity.RolEntity;
import com.spv.spv_backend.infra.Seguridad.Rol.Mapper.RolMapper;
import com.spv.spv_backend.infra.Seguridad.Rol.Repository.RolJpaRepository;

@Component
public class RolRepositoryAdapter implements RolRepositoryPort {

    private final RolJpaRepository jpaRepository;
    private final RolMapper mapper;

    public RolRepositoryAdapter(RolJpaRepository jpaRepository, RolMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Rol> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Rol save(Rol rol) {
        RolEntity entity = mapper.toEntity(rol);
        RolEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Rol> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}