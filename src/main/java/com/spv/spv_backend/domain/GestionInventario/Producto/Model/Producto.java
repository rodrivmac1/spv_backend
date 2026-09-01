package com.spv.spv_backend.domain.GestionInventario.Producto.Model;

public class Producto {

    private Long idProducto;
    private String nombre;
    private Boolean estado;

    public Producto() {
    }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}