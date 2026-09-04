package com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model;

public class ProduccionSemanalInsumosGenerales {

    private Long idProduccionSemanalInsumoGeneral;
    private Long idProduccionSemanal;
    private Long idInsumoGeneral;
    private String nombreInsumo;
    private Double costo;

    public ProduccionSemanalInsumosGenerales() {
    }

    public Long getIdProduccionSemanalInsumoGeneral() { return idProduccionSemanalInsumoGeneral; }
    public void setIdProduccionSemanalInsumoGeneral(Long idProduccionSemanalInsumoGeneral) { this.idProduccionSemanalInsumoGeneral = idProduccionSemanalInsumoGeneral; }

    public Long getIdProduccionSemanal() { return idProduccionSemanal; }
    public void setIdProduccionSemanal(Long idProduccionSemanal) { this.idProduccionSemanal = idProduccionSemanal; }

    public Long getIdInsumoGeneral() { return idInsumoGeneral; }
    public void setIdInsumoGeneral(Long idInsumoGeneral) { this.idInsumoGeneral = idInsumoGeneral; }

    public String getNombreInsumo() { return nombreInsumo; }
    public void setNombreInsumo(String nombreInsumo) { this.nombreInsumo = nombreInsumo; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }
}
