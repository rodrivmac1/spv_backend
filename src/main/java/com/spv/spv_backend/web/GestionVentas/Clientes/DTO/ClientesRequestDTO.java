package com.spv.spv_backend.web.GestionVentas.Clientes.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientesRequestDTO {

    @NotBlank(message = "El nombre del cliente es requerido")
    private String nombre;

    @NotNull(message = "El ID del tipo de cliente es requerido")
    private Long idTipoCliente;
    
    private Boolean estado;
}