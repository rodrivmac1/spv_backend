package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model.ProduccionSemanalInsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Port.ProduccionSemanalInsumosGeneralesRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Entity.ProduccionSemanalInsumosGeneralesEntity;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Mapper.ProduccionSemanalInsumosGeneralesMapper;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Repository.ProduccionSemanalInsumosGeneralesJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProduccionSemanalInsumosGeneralesRepositoryAdapter implements ProduccionSemanalInsumosGeneralesRepositoryPort {

    private final ProduccionSemanalInsumosGeneralesJpaRepository jpaRepository;
    private final ProduccionSemanalInsumosGeneralesMapper mapper;

    @Override
    public List<ProduccionSemanalInsumosGenerales> findByIdProduccionSemanal(Long idProduccionSemanal) {
        return jpaRepository.findByIdProduccionSemanal(idProduccionSemanal).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<ProduccionSemanalInsumosGenerales> insumos) {
        List<ProduccionSemanalInsumosGeneralesEntity> entities = insumos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }
}
