package com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriasPrimasRequestDTO {

    @NotBlank(message = "El nombre de la materia prima es requerido")
    private String nombre;

    @NotNull(message = "Los kilos comprados son requeridos")
    private Double kgComprados;

    @NotNull(message = "Los kilos de rendimiento son requeridos")
    private Double kgRendimiento;
}