package com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Model;

public class ProduccionProductoPresentacion {
    private Long idProduccionProductoPresentacion;
    private Long idProduccionProducto;
    private Long idProductoPresentacion;
    private Double costoProduccion;
    private Double precioVentaSugerido;

    public ProduccionProductoPresentacion() {
    }

    public Long getIdProduccionProductoPresentacion() { return idProduccionProductoPresentacion; }
    public void setIdProduccionProductoPresentacion(Long idProduccionProductoPresentacion) { this.idProduccionProductoPresentacion = idProduccionProductoPresentacion; }

    public Long getIdProduccionProducto() { return idProduccionProducto; }
    public void setIdProduccionProducto(Long idProduccionProducto) { this.idProduccionProducto = idProduccionProducto; }

    public Long getIdProductoPresentacion() { return idProductoPresentacion; }
    public void setIdProductoPresentacion(Long idProductoPresentacion) { this.idProductoPresentacion = idProductoPresentacion; }

    public Double getCostoProduccion() { return costoProduccion; }
    public void setCostoProduccion(Double costoProduccion) { this.costoProduccion = costoProduccion; }

    public Double getPrecioVentaSugerido() { return precioVentaSugerido; }
    public void setPrecioVentaSugerido(Double precioVentaSugerido) { this.precioVentaSugerido = precioVentaSugerido; }
}