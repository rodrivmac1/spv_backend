package com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model.ProductoInsumo;
import com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Port.ProductoInsumoRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Mapper.ProductoInsumoMapper;
import com.spv.spv_backend.infra.GestionInventario.ProductoInsumo.Repository.ProductoInsumoJpaRepository;

@Component
public class ProductoInsumoRepositoryAdapter implements ProductoInsumoRepositoryPort {

    private final ProductoInsumoJpaRepository jpaRepository;
    private final ProductoInsumoMapper mapper;

    public ProductoInsumoRepositoryAdapter(ProductoInsumoJpaRepository jpaRepository, ProductoInsumoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductoInsumo> findByIdProducto(Long idProducto) {
        return jpaRepository.findByIdProducto(idProducto).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoInsumo> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    // Nueva implementación
    @Override
    public Optional<ProductoInsumo> findByIdAndIdProducto(Long idInsumo, Long idProducto) {
        return jpaRepository.findByIdProductoInsumoAndIdProducto(idInsumo, idProducto)
                .map(mapper::toDomain);
    }

    @Override
    public ProductoInsumo save(ProductoInsumo productoInsumo) {
        var entity = mapper.toEntity(productoInsumo);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}