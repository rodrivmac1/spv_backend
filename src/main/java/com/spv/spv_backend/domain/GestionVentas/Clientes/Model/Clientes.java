package com.spv.spv_backend.domain.GestionVentas.Clientes.Model;

public class Clientes {

    private Long idCliente;
    private String nombre;
    private Long idTipoCliente;
    private Boolean estado;

    public Clientes() {
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getIdTipoCliente() { return idTipoCliente; }
    public void setIdTipoCliente(Long idTipoCliente) { this.idTipoCliente = idTipoCliente; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}