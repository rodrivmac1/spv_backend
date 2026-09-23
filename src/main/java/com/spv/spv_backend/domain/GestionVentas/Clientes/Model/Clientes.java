package com.spv.spv_backend.domain.GestionVentas.Clientes.Model;

public class Clientes {

    private Long idCliente;
    private String nombre;
    private Long idTipoCliente;
    private String nombreTipoCliente;
    private Boolean estado;

    public Clientes() {
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getIdTipoCliente() { return idTipoCliente; }
    public void setIdTipoCliente(Long idTipoCliente) { this.idTipoCliente = idTipoCliente; }

    public String getNombreTipoCliente() { return nombreTipoCliente; }
    public void setNombreTipoCliente(String nombreTipoCliente) { this.nombreTipoCliente = nombreTipoCliente; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}