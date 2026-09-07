package com.spv.spv_backend.domain.GestionInventario.ProductoPresentacion.Model;

public class ProductoPresentacion {

    private Long idProductoPresentacion;
    private Long idProducto;
    private String nombre;
    private Double gramos;
    private Double margenGanancia;

    public ProductoPresentacion() {
    }

    public Long getIdProductoPresentacion() { return idProductoPresentacion; }
    public void setIdProductoPresentacion(Long idProductoPresentacion) { this.idProductoPresentacion = idProductoPresentacion; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getGramos() { return gramos; }
    public void setGramos(Double gramos) { this.gramos = gramos; }

    public Double getMargenGanancia() { return margenGanancia; }
    public void setMargenGanancia(Double margenGanancia) { this.margenGanancia = margenGanancia; }
}