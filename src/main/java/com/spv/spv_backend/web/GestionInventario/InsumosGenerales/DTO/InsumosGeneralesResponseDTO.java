package com.spv.spv_backend.web.GestionInventario.InsumosGenerales.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InsumosGeneralesResponseDTO {
    private Long idInsumoGeneral;
    private String nombre;
    private Double costo;
    private LocalDateTime fechaaAgregado;
    private Boolean estado;
}