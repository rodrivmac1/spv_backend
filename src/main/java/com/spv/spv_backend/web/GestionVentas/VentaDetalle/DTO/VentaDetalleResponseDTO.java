package com.spv.spv_backend.web.GestionVentas.VentaDetalle.DTO;

import lombok.Data;

@Data
public class VentaDetalleResponseDTO {
    private Long idVentaDetalle;
    private Long idVenta;
    private Long idProductoPresentacion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}