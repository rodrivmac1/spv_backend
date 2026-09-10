package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity;

import com.spv.spv_backend.infra.GestionVentas.Ventas.Entity.VentasEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "venta_detalle", schema = "public")
@Data
public class VentaDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_detalle")
    private Long idVentaDetalle;

    // Relación ManyToOne con la venta padre para que JPA gestione el id_venta automáticamente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    private VentasEntity venta;

    @Column(name = "id_producto_presentacion", nullable = false)
    private Long idProductoPresentacion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
}