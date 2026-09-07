package com.spv.spv_backend.infra.GestionVentas.TipoCliente.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Model.TipoCliente;
import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Port.TipoClienteRepositoryPort;
import com.spv.spv_backend.infra.GestionVentas.TipoCliente.Mapper.TipoClienteMapper;
import com.spv.spv_backend.infra.GestionVentas.TipoCliente.Repository.TipoClienteJpaRepository;

@Component
public class TipoClienteRepositoryAdapter implements TipoClienteRepositoryPort {

    private final TipoClienteJpaRepository jpaRepository;
    private final TipoClienteMapper mapper;

    public TipoClienteRepositoryAdapter(TipoClienteJpaRepository jpaRepository, TipoClienteMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TipoCliente> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoCliente> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public TipoCliente save(TipoCliente tipoCliente) {
        var entity = mapper.toEntity(tipoCliente);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}