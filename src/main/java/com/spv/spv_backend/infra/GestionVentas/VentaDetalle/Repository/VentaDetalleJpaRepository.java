package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity.VentaDetalleEntity;

@Repository
public interface VentaDetalleJpaRepository extends JpaRepository<VentaDetalleEntity, Long> {
    List<VentaDetalleEntity> findByVenta_IdVenta(Long idVenta);
}