package com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model;

import java.time.LocalDate;

public class NumeroSemana {

    private Long idProduccionSemanal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroSemana;

    public NumeroSemana() {
    }

    public Long getIdProduccionSemanal() { return idProduccionSemanal; }
    public void setIdProduccionSemanal(Long idProduccionSemanal) { this.idProduccionSemanal = idProduccionSemanal; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Integer getNumeroSemana() { return numeroSemana; }
    public void setNumeroSemana(Integer numeroSemana) { this.numeroSemana = numeroSemana; }
}