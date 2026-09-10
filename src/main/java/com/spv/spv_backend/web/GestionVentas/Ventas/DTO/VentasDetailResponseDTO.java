package com.spv.spv_backend.web.GestionVentas.Ventas.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VentasDetailResponseDTO {
    private Long idVenta;
    private Long idCliente;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleItemDTO> detalles;

    @Data
    public static class DetalleItemDTO {
        private Long idVentaDetalle;
        private Long idProductoPresentacion;
        private String nombreProducto;       // Viene de la tabla producto
        private String nombrePresentacion;   // Viene de la tabla producto_presentaciones
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;
    }
}