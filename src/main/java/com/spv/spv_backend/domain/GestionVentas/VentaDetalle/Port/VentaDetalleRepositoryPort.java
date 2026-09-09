package com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Model.VentaDetalle;

public interface VentaDetalleRepositoryPort {
    List<VentaDetalle> findByVentaId(Long idVenta);
    Optional<VentaDetalle> findById(Long id);
    VentaDetalle save(VentaDetalle ventaDetalle);
}