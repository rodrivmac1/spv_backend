package com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoInsumoRequestDTO {

    @NotBlank(message = "El nombre del insumo es requerido")
    private String nombre;

    @NotNull(message = "El costo es requerido")
    private Double costo;
}