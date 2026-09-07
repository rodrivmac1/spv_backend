package com.spv.spv_backend.domain.GestionInventario.Producto.Model;

import java.time.LocalDateTime;

public class Producto {

    private Long idProducto;
    private String nombre;
    private Boolean estado;
    private LocalDateTime fechaCreacion; // Nuevo campo

    public Producto() {
    }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}