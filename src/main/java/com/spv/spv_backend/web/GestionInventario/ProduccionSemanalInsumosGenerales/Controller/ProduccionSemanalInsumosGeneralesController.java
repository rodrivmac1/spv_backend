package com.spv.spv_backend.web.GestionInventario.ProduccionSemanalInsumosGenerales.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionInventario.ProduccionSemanalInsumosGenerales.ProduccionSemanalInsumosGeneralesService;
import com.spv.spv_backend.web.GestionInventario.ProduccionSemanalInsumosGenerales.DTO.ProduccionSemanalInsumosGeneralesResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/produccion-semanal-insumos-generales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProduccionSemanalInsumosGeneralesController {

    private final ProduccionSemanalInsumosGeneralesService service;

    @GetMapping
    public ResponseEntity<List<ProduccionSemanalInsumosGeneralesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
