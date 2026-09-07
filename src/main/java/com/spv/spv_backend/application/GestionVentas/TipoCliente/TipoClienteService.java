package com.spv.spv_backend.application.GestionVentas.TipoCliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Model.TipoCliente;
import com.spv.spv_backend.domain.GestionVentas.TipoCliente.Port.TipoClienteRepositoryPort;
import com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO.TipoClienteRequestDTO;
import com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO.TipoClienteResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoClienteService {

    private final TipoClienteRepositoryPort repositoryPort;

    public List<TipoClienteResponseDTO> obtenerTiposClientesActivos() {
        return repositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TipoClienteResponseDTO obtenerTipoClientePorId(Long id) {
        TipoCliente tipoCliente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado con ID: " + id));
        return mapToResponse(tipoCliente);
    }

    public TipoClienteResponseDTO crearTipoCliente(TipoClienteRequestDTO request) {
        TipoCliente tipoCliente = new TipoCliente();
        tipoCliente.setNombre(request.getNombre());
        tipoCliente.setEstado(true); // Siempre nace activo

        TipoCliente saved = repositoryPort.save(tipoCliente);
        return mapToResponse(saved);
    }

    public TipoClienteResponseDTO editarTipoCliente(Long id, TipoClienteRequestDTO request) {
        TipoCliente existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado con ID: " + id));

        existente.setNombre(request.getNombre());

        TipoCliente updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminacionLogica(Long id) {
        TipoCliente existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de cliente no encontrado con ID: " + id));

        // Borrado lógico: pasa estado a false (inactivo)
        existente.setEstado(false);
        repositoryPort.save(existente);
    }

    private TipoClienteResponseDTO mapToResponse(TipoCliente dom) {
        TipoClienteResponseDTO res = new TipoClienteResponseDTO();
        res.setIdTipoCliente(dom.getIdTipoCliente());
        res.setNombre(dom.getNombre());
        res.setEstado(dom.getEstado());
        return res;
    }
}