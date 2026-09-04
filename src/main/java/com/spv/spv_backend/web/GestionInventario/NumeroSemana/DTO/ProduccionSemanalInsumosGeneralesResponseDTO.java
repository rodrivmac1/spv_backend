package com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO;

import lombok.Data;

@Data
public class ProduccionSemanalInsumosGeneralesResponseDTO {
    private Long idProduccionSemanalInsumoGeneral;
    private Long idInsumoGeneral;
    private String nombreInsumo;
    private Double costo;
}