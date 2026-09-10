package com.spv.spv_backend.infra.GestionVentas.Ventas.Mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.Ventas.Model.Ventas;
import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity.VentaDetalleEntity;
import com.spv.spv_backend.infra.GestionVentas.Ventas.Entity.VentasEntity;

@Component
public class VentasMapper {

    public VentasEntity toEntity(Ventas domain) {
        if (domain == null) return null;

        VentasEntity entity = new VentasEntity();
        entity.setIdVenta(domain.getIdVenta());
        entity.setIdCliente(domain.getIdCliente());
        entity.setFecha(domain.getFecha());
        entity.setTotal(domain.getTotal());

        if (domain.getDetalles() != null) {
            var detallesEntities = domain.getDetalles().stream().map(det -> {
                VentaDetalleEntity detEntity = new VentaDetalleEntity();
                detEntity.setIdVentaDetalle(det.getIdVentaDetalle());
                detEntity.setVenta(entity);
                detEntity.setIdProductoPresentacion(det.getIdProductoPresentacion());
                detEntity.setCantidad(det.getCantidad());
                detEntity.setPrecioUnitario(det.getPrecioUnitario());
                detEntity.setSubtotal(det.getSubtotal());
                return detEntity;
            }).collect(Collectors.toList());
            
            entity.setDetallesEntity(detallesEntities);
        }

        return entity;
    }
}