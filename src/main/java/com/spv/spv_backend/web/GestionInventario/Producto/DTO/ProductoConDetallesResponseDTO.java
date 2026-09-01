package com.spv.spv_backend.web.GestionInventario.Producto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoConDetallesResponseDTO {
    private Long idProducto;
    private String nombre;
    private Boolean estado;
    private String nombreMateriaPrima;
    private Long totalInsumos;
    private Long totalPresentaciones;
}