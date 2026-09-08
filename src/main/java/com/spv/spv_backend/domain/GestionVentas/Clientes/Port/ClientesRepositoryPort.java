package com.spv.spv_backend.domain.GestionVentas.Clientes.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionVentas.Clientes.Model.Clientes;

public interface ClientesRepositoryPort {
    List<Clientes> listActive();
    List<Clientes> findAll();
    Optional<Clientes> findById(Long id);
    Clientes save(Clientes clientes);
}