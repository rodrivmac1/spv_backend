package com.spv.spv_backend.application.GestionInventario.ProduccionSemanalProducto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Importante

import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model.ProduccionSemanalProducto;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Port.ProduccionSemanalProductoRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.ProduccionSemanalProducto.DTO.ProduccionSemanalProductoResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProduccionSemanalProductoService {

    private final ProduccionSemanalProductoRepositoryPort repositoryPort;

    @Transactional(readOnly = true) // <-- Mantiene la sesión abierta
    public List<ProduccionSemanalProductoResponseDTO> obtenerProductosPorSemana(Long idProduccionSemanal) {
        return repositoryPort.findByIdProduccionSemanal(idProduccionSemanal).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // <-- Mantiene la sesión abierta
    public ProduccionSemanalProductoResponseDTO obtenerPorId(Long id) {
        ProduccionSemanalProducto prod = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de producción por producto no encontrado con ID: " + id));
        return mapToResponse(prod);
    }

    private ProduccionSemanalProductoResponseDTO mapToResponse(ProduccionSemanalProducto dom) {
        ProduccionSemanalProductoResponseDTO res = new ProduccionSemanalProductoResponseDTO();
        res.setIdProduccionProducto(dom.getIdProduccionProducto());
        res.setIdProduccionSemanal(dom.getIdProduccionSemanal());
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setKgComprados(dom.getKgComprados());
        res.setCostoInsumos(dom.getCostoInsumos());
        res.setCostoGeneralAsignado(dom.getCostoGeneralAsignado());
        res.setCostoTotalLote(dom.getCostoTotalLote());
        res.setKgRendimiento(dom.getKgRendimiento());
        res.setCosto100g(dom.getCosto100g());
        return res;
    }
}