package com.spv.spv_backend.web.GestionVentas.Ventas.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.spv.spv_backend.web.GestionVentas.VentaDetalle.DTO.VentaDetalleRequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentasRequestDTO {

    @NotNull(message = "El ID del cliente es requerido")
    private Long idCliente;

    @NotNull(message = "La fecha es requerida")
    private LocalDateTime fecha;

    @NotNull(message = "El total es requerido")
    private Double total;

    @NotNull(message = "Los detalles de la venta son requeridos")
    private List<VentaDetalleRequestDTO> detalles;
}