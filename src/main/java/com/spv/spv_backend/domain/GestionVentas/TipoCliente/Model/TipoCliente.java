
package com.spv.spv_backend.domain.GestionVentas.TipoCliente.Model;

public class TipoCliente {

    private Long idTipoCliente;
    private String nombre;
    private Boolean estado;

    public TipoCliente() {
    }

    public Long getIdTipoCliente() { return idTipoCliente; }
    public void setIdTipoCliente(Long idTipoCliente) { this.idTipoCliente = idTipoCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}