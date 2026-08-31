package com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model.ProductoPresentacion;
import com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Port.ProductoPresentacionRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Mapper.ProductoPresentacionMapper;
import com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Repository.ProductoPresentacionJpaRepository;

@Component
public class ProductoPresentacionRepositoryAdapter implements ProductoPresentacionRepositoryPort {

    private final ProductoPresentacionJpaRepository jpaRepository;
    private final ProductoPresentacionMapper mapper;

    public ProductoPresentacionRepositoryAdapter(ProductoPresentacionJpaRepository jpaRepository, ProductoPresentacionMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductoPresentacion> findByIdProducto(Long idProducto) {
        return jpaRepository.findByIdProducto(idProducto).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoPresentacion> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public ProductoPresentacion save(ProductoPresentacion productoPresentacion) {
        var entity = mapper.toEntity(productoPresentacion);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}