package com.spv.spv_backend.web.GestionInventario.Producto.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
}