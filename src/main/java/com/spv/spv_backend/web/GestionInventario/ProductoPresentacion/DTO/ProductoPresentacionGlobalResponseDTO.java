package com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPresentacionGlobalResponseDTO {
    private Long idProducto;
    private Long idProductoPresentacion;
    private String nombreCompleto; // Contendrá la concatenación: nombre presentacion + gramos + nombre producto
}