package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Mapper;

import org.springframework.stereotype.Component;

import com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Model.VentaDetalle;
import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity.VentaDetalleEntity;
import com.spv.spv_backend.infra.GestionVentas.Ventas.Entity.VentasEntity;

@Component
public class VentaDetalleMapper {

    public VentaDetalle toDomain(VentaDetalleEntity entity) {
        if (entity == null) return null;

        VentaDetalle domain = new VentaDetalle();
        domain.setIdVentaDetalle(entity.getIdVentaDetalle());
        domain.setIdVenta(entity.getVenta() != null ? entity.getVenta().getIdVenta() : null);
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
        if (domain.getIdVenta() != null) {
            VentasEntity venta = new VentasEntity();
            venta.setIdVenta(domain.getIdVenta());
            entity.setVenta(venta);
        }
        entity.setIdProductoPresentacion(domain.getIdProductoPresentacion());
        entity.setCantidad(domain.getCantidad());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setSubtotal(domain.getSubtotal());

        return entity;
    }
}