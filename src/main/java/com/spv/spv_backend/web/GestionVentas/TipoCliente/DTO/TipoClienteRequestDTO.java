package com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoClienteRequestDTO {

    @NotBlank(message = "El nombre del tipo de cliente es requerido")
    private String nombre;
    
    private Boolean estado;
}