package com.spv.spv_backend.web.GestionInventario.ProduccionProductoPresentacion.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionInventario.ProduccionProductoPresentacion.ProduccionProductoPresentacionService;
import com.spv.spv_backend.web.GestionInventario.ProduccionProductoPresentacion.DTO.ProduccionProductoPresentacionResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/produccion-semanal/{idProduccionSemanal}/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProduccionProductoPresentacionController {

    private final ProduccionProductoPresentacionService service;

    @GetMapping("/{idProduccionProducto}/presentaciones")
    public ResponseEntity<List<ProduccionProductoPresentacionResponseDTO>> listarPresentacionesPorProducto(
            @PathVariable Long idProduccionSemanal,
            @PathVariable Long idProduccionProducto) {
        return ResponseEntity.ok(service.obtenerPresentacionesPorProducto(
                idProduccionSemanal, idProduccionProducto));
    }
}