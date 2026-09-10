package com.spv.spv_backend.domain.GestionVentas.Ventas.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Ventas {

    private Long idVenta;
    private Long idCliente;
    private LocalDateTime fecha;
    private Double total;
    private List<VentasDetalleDomain> detalles;

    public Ventas() {
    }

    // Getters y Setters
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<VentasDetalleDomain> getDetalles() { return detalles; }
    public void setDetalles(List<VentasDetalleDomain> detalles) { this.detalles = detalles; }

    // Subclase interna para el modelo de dominio de detalle con nombres descriptivos
    public static class VentasDetalleDomain {
        private Long idVentaDetalle;
        private Long idProductoPresentacion;
        private String nombreProducto;
        private String nombrePresentacion;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;

        // Getters y Setters
        public Long getIdVentaDetalle() { return idVentaDetalle; }
        public void setIdVentaDetalle(Long idVentaDetalle) { this.idVentaDetalle = idVentaDetalle; }
        public Long getIdProductoPresentacion() { return idProductoPresentacion; }
        public void setIdProductoPresentacion(Long idProductoPresentacion) { this.idProductoPresentacion = idProductoPresentacion; }
        public String getNombreProducto() { return nombreProducto; }
        public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
        public String getNombrePresentacion() { return nombrePresentacion; }
        public void setNombrePresentacion(String nombrePresentacion) { this.nombrePresentacion = nombrePresentacion; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
        public Double getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
        public Double getSubtotal() { return subtotal; }
        public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    }
}