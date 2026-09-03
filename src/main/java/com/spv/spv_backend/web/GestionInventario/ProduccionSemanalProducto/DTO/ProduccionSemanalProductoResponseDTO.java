package com.spv.spv_backend.web.GestionInventario.ProduccionSemanalProducto.DTO;

import lombok.Data;

@Data
public class ProduccionSemanalProductoResponseDTO {
    private Long idProduccionProducto;
    private Long idProduccionSemanal;
    private Long idProducto;
    private Double kgComprados;
    private Double costoInsumos;
    private Double costoGeneralAsignado;
    private Double costoTotalLote;
    private Double kgRendimiento;
    private Double costo100g;
}