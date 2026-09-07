package com.spv.spv_backend.application.GestionInventario.ProduccionProductoPresentacion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Model.ProduccionProductoPresentacion;
import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Port.ProduccionProductoPresentacionRepositoryPort;
import com.spv.spv_backend.web.GestionInventario.ProduccionProductoPresentacion.DTO.ProduccionProductoPresentacionResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProduccionProductoPresentacionService {

    private final ProduccionProductoPresentacionRepositoryPort repositoryPort;

    @Transactional(readOnly = true)
    public List<ProduccionProductoPresentacionResponseDTO> obtenerPresentacionesPorProducto(
            Long idProduccionSemanal, Long idProduccionProducto) {
        return repositoryPort.findByIdProduccionProductoAndIdProduccionSemanal(
                        idProduccionProducto, idProduccionSemanal)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProduccionProductoPresentacionResponseDTO mapToResponse(ProduccionProductoPresentacion domain) {
        ProduccionProductoPresentacionResponseDTO response = new ProduccionProductoPresentacionResponseDTO();
        response.setIdProduccionProductoPresentacion(domain.getIdProduccionProductoPresentacion());
        response.setIdProduccionProducto(domain.getIdProduccionProducto());
        response.setIdProductoPresentacion(domain.getIdProductoPresentacion());
        response.setCostoProduccion(domain.getCostoProduccion());
        response.setPrecioVentaSugerido(domain.getPrecioVentaSugerido());
        response.setNombre(domain.getNombre());
        response.setGramos(domain.getGramos());
        return response;
    }
}