package com.spv.spv_backend.application.GestionVentas.Clientes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionVentas.Clientes.Model.Clientes;
import com.spv.spv_backend.domain.GestionVentas.Clientes.Port.ClientesRepositoryPort;
import com.spv.spv_backend.web.GestionVentas.Clientes.DTO.ClientesRequestDTO;
import com.spv.spv_backend.web.GestionVentas.Clientes.DTO.ClientesResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final ClientesRepositoryPort repositoryPort;

    public List<ClientesResponseDTO> obtenerClientesActivos() {
        return repositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ClientesResponseDTO obtenerClientePorId(Long id) {
        Clientes cliente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return mapToResponse(cliente);
    }

    public ClientesResponseDTO crearCliente(ClientesRequestDTO request) {
        Clientes cliente = new Clientes();
        cliente.setNombre(request.getNombre());
        cliente.setIdTipoCliente(request.getIdTipoCliente());
        cliente.setEstado(true); // Siempre nace activo

        Clientes saved = repositoryPort.save(cliente);
        return mapToResponse(saved);
    }

    public ClientesResponseDTO editarCliente(Long id, ClientesRequestDTO request) {
        Clientes existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        existente.setNombre(request.getNombre());
        existente.setIdTipoCliente(request.getIdTipoCliente());

        Clientes updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public ClientesResponseDTO alternarEstadoCliente(Long id) {
        Clientes existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Invierte el estado actual (si es true pasa a false, si es false pasa a true)
        existente.setEstado(!existente.getEstado());
        
        Clientes updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    private ClientesResponseDTO mapToResponse(Clientes dom) {
        ClientesResponseDTO res = new ClientesResponseDTO();
        res.setIdCliente(dom.getIdCliente());
        res.setNombre(dom.getNombre());
        res.setIdTipoCliente(dom.getIdTipoCliente());
        res.setEstado(dom.getEstado());
        return res;
    }

    public List<ClientesResponseDTO> obtenerTodosLosClientes() {
        return repositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}