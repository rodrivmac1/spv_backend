package com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumeroSemanaRequestDTO {

    @NotNull(message = "La fecha de inicio es requerida")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es requerida")
    private LocalDate fechaFin;
    
    // Nota: El 'numeroSemana' no se incluye en el RequestDTO porque se calcula automático de forma interna.
}