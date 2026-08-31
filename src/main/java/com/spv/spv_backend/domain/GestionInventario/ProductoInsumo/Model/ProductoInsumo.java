package com.spv.spv_backend.domain.GestionInventario.ProductoInsumo.Model;

public class ProductoInsumo {

    private Long idProductoInsumo;
    private Long idProducto;
    private String nombre;
    private Double costo;

    public ProductoInsumo() {
    }

    public Long getIdProductoInsumo() { return idProductoInsumo; }
    public void setIdProductoInsumo(Long idProductoInsumo) { this.idProductoInsumo = idProductoInsumo; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }
}