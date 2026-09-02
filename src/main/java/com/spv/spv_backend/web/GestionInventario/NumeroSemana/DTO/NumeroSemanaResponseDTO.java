package com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NumeroSemanaResponseDTO {
    private Long idProduccionSemanal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroSemana;
}