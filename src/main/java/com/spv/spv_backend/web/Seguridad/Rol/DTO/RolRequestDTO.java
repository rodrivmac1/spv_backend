package com.spv.spv_backend.web.Seguridad.Rol.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolRequestDTO {

    @NotBlank(message = "El nombre del rol es requerido")
    private String nombre;

    private String descripcion;
    
    private Boolean estado;
}