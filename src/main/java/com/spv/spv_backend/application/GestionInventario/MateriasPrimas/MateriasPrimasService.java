package com.spv.spv_backend.application.GestionInventario.MateriasPrimas;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Model.MateriasPrimas;
import com.spv.spv_backend.domain.GestionInventario.MateriasPrimas.Port.MateriasPrimasRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO.MateriasPrimasRequestDTO;
import com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO.MateriasPrimasResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MateriasPrimasService {

    private final MateriasPrimasRepositoryPort repositoryPort;

    public MateriasPrimasResponseDTO obtenerPorProducto(Long idProducto) {
        MateriasPrimas materiaPrima = repositoryPort.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Materia prima no encontrada para el producto con ID: " + idProducto));
        return mapToResponse(materiaPrima);
    }

    public MateriasPrimasResponseDTO guardarOActualizar(Long idProducto, MateriasPrimasRequestDTO request) {
        // Buscar si ya existe una materia prima asociada a este producto
        MateriasPrimas materiaPrima = repositoryPort.findByIdProducto(idProducto)
                .orElse(new MateriasPrimas());

        materiaPrima.setIdProducto(idProducto);
        materiaPrima.setNombre(request.getNombre());
        materiaPrima.setKgComprados(request.getKgComprados());
        materiaPrima.setKgRendimiento(request.getKgRendimiento());

        MateriasPrimas saved = repositoryPort.save(materiaPrima);
        return mapToResponse(saved);
    }

    private MateriasPrimasResponseDTO mapToResponse(MateriasPrimas dom) {
        MateriasPrimasResponseDTO res = new MateriasPrimasResponseDTO();
        res.setIdMateriaPrima(dom.getIdMateriaPrima());
        res.setIdProducto(dom.getIdProducto());
        res.setNombre(dom.getNombre());
        res.setKgComprados(dom.getKgComprados());
        res.setKgRendimiento(dom.getKgRendimiento());
        return res;
    }
}