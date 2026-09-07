package com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model;

public class MateriasPrimas {

    private Long idMateriaPrima;
    private Long idProducto;
    private String nombre;
    private Double kgComprados;
    private Double kgRendimiento;

    public MateriasPrimas() {
    }

    public Long getIdMateriaPrima() { return idMateriaPrima; }
    public void setIdMateriaPrima(Long idMateriaPrima) { this.idMateriaPrima = idMateriaPrima; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getKgComprados() { return kgComprados; }
    public void setKgComprados(Double kgComprados) { this.kgComprados = kgComprados; }

    public Double getKgRendimiento() { return kgRendimiento; }
    public void setKgRendimiento(Double kgRendimiento) { this.kgRendimiento = kgRendimiento; }
}