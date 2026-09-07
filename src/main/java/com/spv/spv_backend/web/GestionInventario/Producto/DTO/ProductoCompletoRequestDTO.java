package com.spv.spv_backend.web.GestionInventario.Producto.DTO;

import java.util.List;

import com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO.MateriasPrimasRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO.ProductoInsumoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCompletoRequestDTO {

    @NotBlank(message = "El nombre del producto es requerido")
    private String nombre;

    // Lista de insumos específicos opcionales u obligatorios
    private List<@Valid ProductoInsumoRequestDTO> insumos;

    // Materia prima base asociada al rendimiento
    @Valid
    private MateriasPrimasRequestDTO materiaPrima;

    // Lista de presentaciones del producto
    private List<@Valid ProductoPresentacionRequestDTO> presentaciones;
}