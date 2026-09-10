package com.spv.spv_backend.web.GestionVentas.Ventas.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentasListResponseDTO {
    private Long idVenta;
    private String nombreCliente;     // Campo nombre de la tabla clientes
    private String tipoCliente;       // Campo nombre de la tabla tipo_cliente
    private LocalDateTime fecha;
    private Double total;
}