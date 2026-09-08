package com.spv.spv_backend.web.GestionVentas.Clientes.DTO;

import lombok.Data;

@Data
public class ClientesResponseDTO {
    private Long idCliente;
    private String nombre;
    private Long idTipoCliente;
    private Boolean estado;
}