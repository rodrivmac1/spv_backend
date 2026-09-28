package com.spv.spv_backend.web.Seguridad.Usuario.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotNull(message = "El ID del rol es requerido")
    private Long idRol;

    @NotBlank(message = "El nombre del usuario es requerido")
    private String nombre;

    @NotBlank(message = "El nombre de usuario es requerido")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    private Boolean estado;
}