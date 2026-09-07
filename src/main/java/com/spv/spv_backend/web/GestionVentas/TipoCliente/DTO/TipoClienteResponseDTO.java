package com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO;

import lombok.Data;

@Data
public class TipoClienteResponseDTO {
    private Long idTipoCliente;
    private String nombre;
    private Boolean estado;
}