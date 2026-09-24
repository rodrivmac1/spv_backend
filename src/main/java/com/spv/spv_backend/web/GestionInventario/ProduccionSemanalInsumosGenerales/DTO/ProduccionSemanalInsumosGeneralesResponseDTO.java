package com.spv.spv_backend.web.GestionInventario.ProduccionSemanalInsumosGenerales.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProduccionSemanalInsumosGeneralesResponseDTO {
    private Long idProduccionSemanalInsumoGeneral;
    private Long idProduccionSemanal;
    private Long idInsumoGeneral;
    private String nombreInsumo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double costo;
}
