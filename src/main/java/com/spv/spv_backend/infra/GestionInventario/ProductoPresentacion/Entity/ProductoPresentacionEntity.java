package com.spv.spv_backend.infra.GestionInventario.ProductoPresentacion.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto_presentaciones", schema = "public")
@Data
public class ProductoPresentacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_presentacion")
    private Long idProductoPresentacion;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "gramos", nullable = false)
    private Double gramos;

    @Column(name = "margen_ganancia", nullable = false)
    private Double margenGanancia;
}