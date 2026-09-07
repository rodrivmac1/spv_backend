package com.spv.spv_backend.domain.GestionVentas.TipoCliente.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Model.TipoCliente;

public interface TipoClienteRepositoryPort {
    List<TipoCliente> listActive();
    Optional<TipoCliente> findById(Long id);
    TipoCliente save(TipoCliente tipoCliente);
}