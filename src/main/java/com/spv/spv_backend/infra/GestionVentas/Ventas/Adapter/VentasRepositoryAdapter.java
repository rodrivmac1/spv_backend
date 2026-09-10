package com.spv.spv_backend.infra.GestionVentas.Ventas.Adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.Ventas.Model.Ventas;
import com.spv.spv_backend.domain.GestionVentas.Ventas.Port.VentasRepositoryPort;
import com.spv.spv_backend.infra.GestionVentas.Ventas.Entity.VentasEntity;
import com.spv.spv_backend.infra.GestionVentas.Ventas.Mapper.VentasMapper;
import com.spv.spv_backend.infra.GestionVentas.Ventas.Repository.VentasJpaRepository;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO;

@Component
public class VentasRepositoryAdapter implements VentasRepositoryPort {

    private final VentasJpaRepository jpaRepository;
    private final VentasMapper mapper;

    public VentasRepositoryAdapter(VentasJpaRepository jpaRepository, VentasMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Ventas save(Ventas ventas) {
        VentasEntity entity = mapper.toEntity(ventas);
        VentasEntity savedEntity = jpaRepository.save(entity);

        ventas.setIdVenta(savedEntity.getIdVenta());
        return ventas;
    }

    @Override
    public Optional<Ventas> findByIdWithDetails(Long idVenta) {
        return jpaRepository.findById(idVenta).map(entity -> {
            Ventas domain = new Ventas();
            domain.setIdVenta(entity.getIdVenta());
            domain.setIdCliente(entity.getIdCliente());
            domain.setFecha(entity.getFecha());
            domain.setTotal(entity.getTotal());
            return domain;
        });
    }

    @Override
    public List<VentasListResponseDTO> findAllListado() {
        return jpaRepository.findAllVentasConDetalleCliente();
    }

    @Override
    public void deleteById(Long idVenta) {
        jpaRepository.deleteById(idVenta);
    }
}