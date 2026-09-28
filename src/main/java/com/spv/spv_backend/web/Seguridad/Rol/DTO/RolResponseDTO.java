package com.spv.spv_backend.web.Seguridad.Rol.DTO;

import lombok.Data;

@Data
public class RolResponseDTO {
    private Long idRol;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}