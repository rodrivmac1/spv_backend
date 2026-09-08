package com.spv.spv_backend.infra.GestionVentas.Clientes.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.Clientes.Model.Clientes;
import com.spv.spv_backend.domain.GestionVentas.Clientes.Port.ClientesRepositoryPort;
import com.spv.spv_backend.infra.GestionVentas.Clientes.Mapper.ClientesMapper;
import com.spv.spv_backend.infra.GestionVentas.Clientes.Repository.ClientesJpaRepository;

@Component
public class ClientesRepositoryAdapter implements ClientesRepositoryPort {

    private final ClientesJpaRepository jpaRepository;
    private final ClientesMapper mapper;

    public ClientesRepositoryAdapter(ClientesJpaRepository jpaRepository, ClientesMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Clientes> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Clientes> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Clientes save(Clientes clientes) {
        var entity = mapper.toEntity(clientes);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    @Override
    public List<Clientes> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}