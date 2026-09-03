package com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model;

public class ProduccionSemanalProducto {

    private Long idProduccionProducto;
    private Long idProduccionSemanal;
    private Long idProducto;
    private Double kgComprados;
    private Double costoInsumos;
    private Double costoGeneralAsignado;
    private Double costoTotalLote;
    private Double kgRendimiento;
    private Double costo100g;

    public ProduccionSemanalProducto() {
    }

    public Long getIdProduccionProducto() { return idProduccionProducto; }
    public void setIdProduccionProducto(Long idProduccionProducto) { this.idProduccionProducto = idProduccionProducto; }

    public Long getIdProduccionSemanal() { return idProduccionSemanal; }
    public void setIdProduccionSemanal(Long idProduccionSemanal) { this.idProduccionSemanal = idProduccionSemanal; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public Double getKgComprados() { return kgComprados; }
    public void setKgComprados(Double kgComprados) { this.kgComprados = kgComprados; }

    public Double getCostoInsumos() { return costoInsumos; }
    public void setCostoInsumos(Double costoInsumos) { this.costoInsumos = costoInsumos; }

    public Double getCostoGeneralAsignado() { return costoGeneralAsignado; }
    public void setCostoGeneralAsignado(Double costoGeneralAsignado) { this.costoGeneralAsignado = costoGeneralAsignado; }

    public Double getCostoTotalLote() { return costoTotalLote; }
    public void setCostoTotalLote(Double costoTotalLote) { this.costoTotalLote = costoTotalLote; }

    public Double getKgRendimiento() { return kgRendimiento; }
    public void setKgRendimiento(Double kgRendimiento) { this.kgRendimiento = kgRendimiento; }

    public Double getCosto100g() { return costo100g; }
    public void setCosto100g(Double costo100g) { this.costo100g = costo100g; }
}