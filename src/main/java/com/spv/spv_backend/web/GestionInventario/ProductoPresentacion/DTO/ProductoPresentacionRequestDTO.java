package com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPresentacionRequestDTO {

    @NotBlank(message = "El nombre de la presentación es requerido")
    private String nombre;

    @NotNull(message = "Los gramos son requeridos")
    private Double gramos;

    @NotNull(message = "El margen de ganancia es requerido")
    private Double margenGanancia;
}