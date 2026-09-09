package com.spv.spv_backend.domain.GestionVentas.VentaDetalle.Model;

public class VentaDetalle {

    private Long idVentaDetalle;
    private Long idVenta;
    private Long idProductoPresentacion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public VentaDetalle() {
    }

    public Long getIdVentaDetalle() { return idVentaDetalle; }
    public void setIdVentaDetalle(Long idVentaDetalle) { this.idVentaDetalle = idVentaDetalle; }

    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }

    public Long getIdProductoPresentacion() { return idProductoPresentacion; }
    public void setIdProductoPresentacion(Long idProductoPresentacion) { this.idProductoPresentacion = idProductoPresentacion; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}