package com.spv.spv_backend.domain.GestionVentas.Ventas.Port;

import java.util.List;
import java.util.Optional;

import com.spv.spv_backend.domain.GestionVentas.Ventas.Model.Ventas;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO;

public interface VentasRepositoryPort {
    Ventas save(Ventas ventas);
    Optional<Ventas> findByIdWithDetails(Long idVenta);
    List<VentasListResponseDTO> findAllListado();
    void deleteById(Long idVenta);
}