package com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model.NumeroSemana;
import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Port.NumeroSemanaRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Mapper.NumeroSemanaMapper;
import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Repository.NumeroSemanaJpaRepository;

@Component
public class NumeroSemanaRepositoryAdapter implements NumeroSemanaRepositoryPort {

    private final NumeroSemanaJpaRepository jpaRepository;
    private final NumeroSemanaMapper mapper;

    public NumeroSemanaRepositoryAdapter(NumeroSemanaJpaRepository jpaRepository, NumeroSemanaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<NumeroSemana> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NumeroSemana> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public NumeroSemana save(NumeroSemana numeroSemana) {
        var entity = mapper.toEntity(numeroSemana);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Integer findMaxNumeroSemanaByYear(int year) {
        return jpaRepository.findMaxNumeroSemanaByYear(year).orElse(0);
    }
}