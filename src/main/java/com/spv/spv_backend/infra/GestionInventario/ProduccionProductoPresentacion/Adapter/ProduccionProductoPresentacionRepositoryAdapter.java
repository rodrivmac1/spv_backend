package com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Adapter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Model.ProduccionProductoPresentacion;
import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Port.ProduccionProductoPresentacionRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity.ProduccionProductoPresentacionEntity;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Mapper.ProduccionProductoPresentacionMapper;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Repository.ProduccionProductoPresentacionJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProduccionProductoPresentacionRepositoryAdapter implements ProduccionProductoPresentacionRepositoryPort {

    private final ProduccionProductoPresentacionJpaRepository jpaRepository;
    private final ProduccionProductoPresentacionMapper mapper;

    @Override
    public List<ProduccionProductoPresentacion> saveAll(List<ProduccionProductoPresentacion> lista) {
        List<ProduccionProductoPresentacionEntity> entities = lista.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        List<ProduccionProductoPresentacionEntity> savedEntities = jpaRepository.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProduccionProductoPresentacion> findByIdProduccionProducto(Long idProduccionProducto) {
        return jpaRepository.findByIdProduccionProducto(idProduccionProducto).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}