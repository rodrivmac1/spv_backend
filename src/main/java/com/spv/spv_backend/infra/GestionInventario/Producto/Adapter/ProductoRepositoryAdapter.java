package com.spv.spv_backend.infra.GestionInventario.Producto.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionInventario.Producto.Model.Producto;
import com.spv.spv_backend.domain.GestionInventario.Producto.Port.ProductoRepositoryPort;
import com.spv.spv_backend.infra.GestionInventario.Producto.Mapper.ProductoMapper;
import com.spv.spv_backend.infra.GestionInventario.Producto.Repository.ProductoJpaRepository;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO;

@Component
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository jpaRepository;
    private final ProductoMapper mapper;

    public ProductoRepositoryAdapter(ProductoJpaRepository jpaRepository, ProductoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Producto> listActive() {
        return jpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoConDetallesResponseDTO> listActiveWithDetails() {
        return jpaRepository.listarProductosConDetallesActivos();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Producto save(Producto producto) {
        var entity = mapper.toEntity(producto);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}