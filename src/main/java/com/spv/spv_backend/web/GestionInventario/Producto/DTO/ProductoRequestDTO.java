package com.spv.spv_backend.web.GestionInventario.Producto.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {
    @NotBlank(message = "El nombre del producto es requerido")
    private String nombre;
}