package com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Port.MateriasPrimasRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Mapper.MateriasPrimasMapper;
import com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Repository.MateriasPrimasJpaRepository;

@Component
public class MateriasPrimasRepositoryAdapter implements MateriasPrimasRepositoryPort {

    private final MateriasPrimasJpaRepository jpaRepository;
    private final MateriasPrimasMapper mapper;

    public MateriasPrimasRepositoryAdapter(MateriasPrimasJpaRepository jpaRepository, MateriasPrimasMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<MateriasPrimas> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<MateriasPrimas> findByIdProducto(Long idProducto) {
        return jpaRepository.findByIdProducto(idProducto).map(mapper::toDomain);
    }

    @Override
    public MateriasPrimas save(MateriasPrimas materiasPrimas) {
        var entity = mapper.toEntity(materiasPrimas);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}