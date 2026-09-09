package com.spv.spv_backend.infra.GestionVentas.VentaDetalle.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "venta_detalle", schema = "public")
@Data
public class VentaDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_detalle")
    private Long idVentaDetalle;

    @Column(name = "id_venta", nullable = false)
    private Long idVenta;

    @Column(name = "id_producto_presentacion", nullable = false)
    private Long idProductoPresentacion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
}