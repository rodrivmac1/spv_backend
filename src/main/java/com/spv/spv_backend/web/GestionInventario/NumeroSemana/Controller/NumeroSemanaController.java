package com.spv.spv_backend.web.GestionInventario.NumeroSemana.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionInventario.NumeroSemana.NumeroSemanaService;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaRequestDTO;
import com.spv.spv_backend.web.GestionInventario.NumeroSemana.DTO.NumeroSemanaResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/numero-semana")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NumeroSemanaController {

    private final NumeroSemanaService numeroSemanaService;

    @GetMapping
    public ResponseEntity<List<NumeroSemanaResponseDTO>> listarSemanas() {
        return ResponseEntity.ok(numeroSemanaService.listarSemanas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NumeroSemanaResponseDTO> obtenerSemanaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(numeroSemanaService.obtenerSemanaPorId(id));
    }

    @PostMapping
    public ResponseEntity<NumeroSemanaResponseDTO> crearSemana(@Valid @RequestBody NumeroSemanaRequestDTO request) {
        NumeroSemanaResponseDTO nuevaSemana = numeroSemanaService.crearSemana(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSemana);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumeroSemanaResponseDTO> editarSemana(
            @PathVariable Long id,
            @Valid @RequestBody NumeroSemanaRequestDTO request) {
        NumeroSemanaResponseDTO actualizada = numeroSemanaService.editarSemana(id, request);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSemana(@PathVariable Long id) {
        numeroSemanaService.eliminarSemana(id);
        return ResponseEntity.noContent().build();
    }
}