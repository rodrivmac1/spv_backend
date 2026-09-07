package com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model;

import java.time.LocalDateTime;

public class InsumosGenerales {

    private Long idInsumoGeneral;
    private String nombre;
    private Double costo;
    private LocalDateTime fechaAgregado;
    private Boolean estado;

    public InsumosGenerales() {
    }

    public Long getIdInsumoGeneral() { return idInsumoGeneral; }
    public void setIdInsumoGeneral(Long idInsumoGeneral) { this.idInsumoGeneral = idInsumoGeneral; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public LocalDateTime getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(LocalDateTime fechaAgregado) { this.fechaAgregado = fechaAgregado; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}