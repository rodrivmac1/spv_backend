package com.spv.spv_backend.web.GestionInventario.ProduccionSemanalProducto.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionInventario.ProduccionSemanalProducto.ProduccionSemanalProductoService;
import com.spv.spv_backend.web.GestionInventario.ProduccionSemanalProducto.DTO.ProduccionSemanalProductoResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/produccion-semanal/{idProduccionSemanal}/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProduccionSemanalProductoController {

    private final ProduccionSemanalProductoService produccionSemanalProductoService;

    // GET: Listar todos los cálculos de los productos asociados a una semana específica
    @GetMapping
    public ResponseEntity<List<ProduccionSemanalProductoResponseDTO>> listarPorSemana(
            @PathVariable Long idProduccionSemanal) {
        return ResponseEntity.ok(produccionSemanalProductoService.obtenerProductosPorSemana(idProduccionSemanal));
    }

    // GET: Obtener el detalle de cálculo de un producto en específico dentro de la semana
    @GetMapping("/{id}")
    public ResponseEntity<ProduccionSemanalProductoResponseDTO> obtenerPorId(
            @PathVariable Long idProduccionSemanal,
            @PathVariable Long id) {
        return ResponseEntity.ok(produccionSemanalProductoService.obtenerPorId(id));
    }
}