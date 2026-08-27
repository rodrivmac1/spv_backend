package com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model.InsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Port.InsumosGeneralesRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Mapper.InsumosGeneralesMapper;
import com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Repository.InsumosGeneralesJpaRepository;

@Component
public class InsumosGeneralesRepositoryAdapter implements InsumosGeneralesRepositoryPort {

    private final InsumosGeneralesJpaRepository jpaRepository;
    private final InsumosGeneralesMapper mapper;

    public InsumosGeneralesRepositoryAdapter(InsumosGeneralesJpaRepository jpaRepository, InsumosGeneralesMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<InsumosGenerales> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InsumosGenerales> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public InsumosGenerales save(InsumosGenerales insumosGenerales) {
        var entity = mapper.toEntity(insumosGenerales);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}