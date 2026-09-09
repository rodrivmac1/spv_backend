package com.spv.spv_backend.web.GestionVentas.VentaDetalle.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDetalleRequestDTO {

    @NotNull(message = "El producto presentación es requerido")
    private Long idProductoPresentacion;

    @NotNull(message = "La cantidad es requerida")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es requerido")
    private Double precioUnitario;

    @NotNull(message = "El subtotal es requerido")
    private Double subtotal;
}