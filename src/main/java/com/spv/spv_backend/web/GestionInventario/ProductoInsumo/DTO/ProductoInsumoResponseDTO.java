package com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO;

import lombok.Data;

@Data
public class ProductoInsumoResponseDTO {
    private Long idProductoInsumo;
    private Long idProducto;
    private String nombre;
    private Double costo;
}