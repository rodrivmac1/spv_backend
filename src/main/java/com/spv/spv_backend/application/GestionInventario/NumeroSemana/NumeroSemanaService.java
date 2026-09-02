package com.spv.spv_backend.application.GestionInventario.NumeroSemana;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Model.NumeroSemana;
import com.spv.spv_backend.domain.GestionInventario.NumeroSemana.Port.NumeroSemanaRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaRequestDTO;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NumeroSemanaService {

    private final NumeroSemanaRepositoryPort repositoryPort;

    public List<NumeroSemanaResponseDTO> listarSemanas() {
        return repositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NumeroSemanaResponseDTO obtenerSemanaPorId(Long id) {
        NumeroSemana semana = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Semana de producción no encontrada con ID: " + id));
        return mapToResponse(semana);
    }

    public NumeroSemanaResponseDTO crearSemana(NumeroSemanaRequestDTO request) {
        int year = request.getFechaInicio().getYear();

        // Obtiene el número de semana más alto del año correspondiente y le suma 1 (si no hay registros, empieza en 1)
        Integer maxSemana = repositoryPort.findMaxNumeroSemanaByYear(year);
        int siguienteNumeroSemana = maxSemana + 1;

        NumeroSemana semana = new NumeroSemana();
        semana.setFechaInicio(request.getFechaInicio());
        semana.setFechaFin(request.getFechaFin());
        semana.setNumeroSemana(siguienteNumeroSemana); // Folio automático que se reinicia por año

        NumeroSemana saved = repositoryPort.save(semana);
        return mapToResponse(saved);
    }

    public NumeroSemanaResponseDTO editarSemana(Long id, NumeroSemanaRequestDTO request) {
        NumeroSemana existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Semana de producción no encontrada con ID: " + id));

        existente.setFechaInicio(request.getFechaInicio());
        existente.setFechaFin(request.getFechaFin());
        // El número de semana no suele modificarse al editar fechas, pero se conservan las fechas nuevas.

        NumeroSemana updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public void eliminarSemana(Long id) {
        if (!repositoryPort.findById(id).isPresent()) {
            throw new RuntimeException("Semana de producción no encontrada con ID: " + id);
        }
        repositoryPort.delete(id);
    }

    private NumeroSemanaResponseDTO mapToResponse(NumeroSemana dom) {
        NumeroSemanaResponseDTO res = new NumeroSemanaResponseDTO();
        res.setIdProduccionSemanal(dom.getIdProduccionSemanal());
        res.setFechaInicio(dom.getFechaInicio());
        res.setFechaFin(dom.getFechaFin());
        res.setNumeroSemana(dom.getNumeroSemana());
        return res;
    }
}