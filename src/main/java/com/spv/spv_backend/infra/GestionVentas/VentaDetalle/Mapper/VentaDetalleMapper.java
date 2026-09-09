package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Model.VentaDetalle;
import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity.VentaDetalleEntity;

@Component
public class VentaDetalleMapper {

    public VentaDetalle toDomain(VentaDetalleEntity entity) {
        if (entity == null) return null;

        VentaDetalle domain = new VentaDetalle();
        domain.setIdVentaDetalle(entity.getIdVentaDetalle());
        domain.setIdVenta(entity.getIdVenta());
        domain.setIdProductoPresentacion(entity.getIdProductoPresentacion());
        domain.setCantidad(entity.getCantidad());
        domain.setPrecioUnitario(entity.getPrecioUnitario());
        domain.setSubtotal(entity.getSubtotal());

        return domain;
    }

    public VentaDetalleEntity toEntity(VentaDetalle domain) {
        if (domain == null) return null;

        VentaDetalleEntity entity = new VentaDetalleEntity();
        entity.setIdVentaDetalle(domain.getIdVentaDetalle());
        entity.setIdVenta(domain.getIdVenta());
        entity.setIdProductoPresentacion(domain.getIdProductoPresentacion());
        entity.setCantidad(domain.getCantidad());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setSubtotal(domain.getSubtotal());

        return entity;
    }
}