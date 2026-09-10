package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Model.VentaDetalle;
import com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Port.VentaDetalleRepositoryPort;
import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Mapper.VentaDetalleMapper;
import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Repository.VentaDetalleJpaRepository;

@Component
public class VentaDetalleRepositoryAdapter implements VentaDetalleRepositoryPort {

    private final VentaDetalleJpaRepository jpaRepository;
    private final VentaDetalleMapper mapper;

    public VentaDetalleRepositoryAdapter(VentaDetalleJpaRepository jpaRepository, VentaDetalleMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<VentaDetalle> findByVentaId(Long idVenta) {
        return jpaRepository.findByVenta_IdVenta(idVenta).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VentaDetalle> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public VentaDetalle save(VentaDetalle ventaDetalle) {
        var entity = mapper.toEntity(ventaDetalle);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}