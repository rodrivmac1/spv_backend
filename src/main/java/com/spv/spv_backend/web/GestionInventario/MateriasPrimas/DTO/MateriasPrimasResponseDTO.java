package com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO;

import lombok.Data;

@Data
public class MateriasPrimasResponseDTO {
    private Long idMateriaPrima;
    private Long idProducto;
    private String nombre;
    private Double kgComprados;
    private Double kgRendimiento;
}