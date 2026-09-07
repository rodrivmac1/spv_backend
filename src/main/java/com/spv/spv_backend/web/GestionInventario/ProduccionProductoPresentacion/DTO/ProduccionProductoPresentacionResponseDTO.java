package com.spv.spv_backend.web.GestionInventario.ProduccionProductoPresentacion.DTO;

import lombok.Data;

@Data
public class ProduccionProductoPresentacionResponseDTO {
    private Long idProduccionProductoPresentacion;
    private Long idProduccionProducto;
    private Long idProductoPresentacion;
    private Double costoProduccion;
    private Double precioVentaSugerido;
    private String nombre;
    private Double gramos;
}
