package com.spv.spv_backend.web.Seguridad.Usuario.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long idUsuario;
    private Long idRol;
    private String nombreRol; // Útil para mostrar el nombre del rol directamente en la tabla del frontend
    private String nombre;
    private String correo;
    private LocalDateTime ultimoAcceso;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
}