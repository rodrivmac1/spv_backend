package com.spv.spv_backend.application.GestionInventario.InsumosGenerales;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Model.InsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.InsumosGenerales.Port.InsumosGeneralesRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.InsumosGenerales.DTO.InsumosGeneralesRequestDTO;
import com.spv.spv_backend.web.GestionInventario.InsumosGenerales.DTO.InsumosGeneralesResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsumosGeneralesService {

    private final InsumosGeneralesRepositoryPort repositoryPort;

    // 1. Obtener solo los que tengan estado en true
    public List<InsumosGeneralesResponseDTO> obtenerInsumosActivos() {
        return repositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 2. Obtener insumo general por ID
    public InsumosGeneralesResponseDTO obtenerInsumoPorId(Long id) {
        InsumosGenerales insumo = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo general no encontrado con ID: " + id));
        return mapToResponse(insumo);
    }

    public InsumosGeneralesResponseDTO crearInsumo(InsumosGeneralesRequestDTO request) {
        InsumosGenerales insumo = new InsumosGenerales();
        insumo.setNombre(request.getNombre());
        insumo.setCosto(request.getCosto());
        insumo.setFechaaAgregado(LocalDateTime.now()); // Automática al crear
        insumo.setEstado(true); // Siempre nace activo

        InsumosGenerales saved = repositoryPort.save(insumo);
        return mapToResponse(saved);
    }

    // 3. Edición restringida únicamente a nombre y costo
    public InsumosGeneralesResponseDTO editarInsumo(Long id, InsumosGeneralesRequestDTO request) {
        InsumosGenerales existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo general no encontrado con ID: " + id));

        // Solo se actualizan estos dos campos; fecha y estado quedan intactos
        existente.setNombre(request.getNombre());
        existente.setCosto(request.getCosto());

        InsumosGenerales updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminacionLogica(Long id) {
        InsumosGenerales existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo general no encontrado con ID: " + id));

        // Borrado lógico: pasa estado de true(1) a false(0)
        existente.setEstado(false);
        repositoryPort.save(existente);
    }

    private InsumosGeneralesResponseDTO mapToResponse(InsumosGenerales dom) {
        InsumosGeneralesResponseDTO res = new InsumosGeneralesResponseDTO();
        res.setIdInsumoGeneral(dom.getIdInsumoGeneral());
        res.setNombre(dom.getNombre());
        res.setCosto(dom.getCosto());
        res.setFechaaAgregado(dom.getFechaaAgregado());
        res.setEstado(dom.getEstado());
        return res;
    }
}