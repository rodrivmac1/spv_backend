package com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO;

import lombok.Data;

@Data
public class ProductoPresentacionResponseDTO {
    private Long idProductoPresentacion;
    private Long idProducto;
    private String nombre;
    private Double gramos;
    private Double margenGanancia;
}