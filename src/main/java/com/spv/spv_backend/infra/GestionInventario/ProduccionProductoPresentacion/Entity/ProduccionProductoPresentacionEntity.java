package com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produccion_producto_presentacion", schema = "public")
@Data
public class ProduccionProductoPresentacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_producto_presentacion")
    private Long idProduccionProductoPresentacion;

    @Column(name = "id_produccion_producto", nullable = false)
    private Long idProduccionProducto;

    @Column(name = "id_producto_presentacion", nullable = false)
    private Long idProductoPresentacion;

    @Column(name = "costo_produccion", nullable = false)
    private Double costoProduccion;

    @Column(name = "precio_venta_sugerido", nullable = false)
    private Double precioVentaSugerido;
}