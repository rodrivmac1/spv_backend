package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model.ProduccionSemanalProducto;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Port.ProduccionSemanalProductoRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity.ProduccionSemanalProductoEntity;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Mapper.ProduccionSemanalProductoMapper;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Repository.ProduccionSemanalProductoJpaRepository;

@Component
public class ProduccionSemanalProductoRepositoryAdapter implements ProduccionSemanalProductoRepositoryPort {

    private final ProduccionSemanalProductoJpaRepository jpaRepository;
    private final ProduccionSemanalProductoMapper mapper;

    public ProduccionSemanalProductoRepositoryAdapter(ProduccionSemanalProductoJpaRepository jpaRepository, ProduccionSemanalProductoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProduccionSemanalProducto> findByIdProduccionSemanal(Long idProduccionSemanal) {
        return jpaRepository.findByIdProduccionSemanal(idProduccionSemanal).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProduccionSemanalProducto> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public ProduccionSemanalProducto save(ProduccionSemanalProducto producto) {
        ProduccionSemanalProductoEntity entity = mapper.toEntity(producto);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void saveAll(List<ProduccionSemanalProducto> productos) {
        List<ProduccionSemanalProductoEntity> entities = productos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }
}