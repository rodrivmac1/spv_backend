package com.spv.spv_backend.application.GestionInventario.ProduccionSemanalInsumosGenerales;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model.ProduccionSemanalInsumosGenerales;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Port.ProduccionSemanalInsumosGeneralesRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.ProduccionSemanalInsumosGenerales.DTO.ProduccionSemanalInsumosGeneralesResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProduccionSemanalInsumosGeneralesService {

    private final ProduccionSemanalInsumosGeneralesRepositoryPort repositoryPort;

    @Transactional(readOnly = true)
    public List<ProduccionSemanalInsumosGeneralesResponseDTO> listarTodos() {
        return repositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProduccionSemanalInsumosGeneralesResponseDTO mapToResponse(
            ProduccionSemanalInsumosGenerales domain) {
        ProduccionSemanalInsumosGeneralesResponseDTO response =
                new ProduccionSemanalInsumosGeneralesResponseDTO();
        response.setIdProduccionSemanalInsumoGeneral(domain.getIdProduccionSemanalInsumoGeneral());
        response.setIdProduccionSemanal(domain.getIdProduccionSemanal());
        response.setIdInsumoGeneral(domain.getIdInsumoGeneral());
        response.setNombreInsumo(domain.getNombreInsumo());
        response.setFechaInicio(domain.getFechaInicio());
        response.setFechaFin(domain.getFechaFin());
        response.setCosto(domain.getCosto());
        return response;
    }
}