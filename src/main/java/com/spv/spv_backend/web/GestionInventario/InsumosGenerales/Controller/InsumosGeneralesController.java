package com.spv.spv_backend.web.GestionInventario.InsumosGenerales.Controller;

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

import com.spv.spv_backend.application.GestionInventario.InsumosGenerales.InsumosGeneralesService;
import com.spv.spv_backend.web.GestionInventario.InsumosGenerales.DTO.InsumosGeneralesRequestDTO;
import com.spv.spv_backend.web.GestionInventario.InsumosGenerales.DTO.InsumosGeneralesResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/insumos-generales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InsumosGeneralesController {

    private final InsumosGeneralesService insumosGeneralesService;

    // GET: Lista únicamente los insumos activos (estado = true)
    @GetMapping
    public ResponseEntity<List<InsumosGeneralesResponseDTO>> listarInsumosActivos() {
        return ResponseEntity.ok(insumosGeneralesService.obtenerInsumosActivos());
    }

    // NUEVO GET: Obtener un insumo específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InsumosGeneralesResponseDTO> obtenerInsumoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(insumosGeneralesService.obtenerInsumoPorId(id));
    }

    @PostMapping
    public ResponseEntity<InsumosGeneralesResponseDTO> crearInsumo(@Valid @RequestBody InsumosGeneralesRequestDTO request) {
        InsumosGeneralesResponseDTO nuevoInsumo = insumosGeneralesService.crearInsumo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInsumo);
    }

    // PUT: Edita únicamente nombre y costo
    @PutMapping("/{id}")
    public ResponseEntity<InsumosGeneralesResponseDTO> editarInsumo(
            @PathVariable Long id, 
            @Valid @RequestBody InsumosGeneralesRequestDTO request) {
        InsumosGeneralesResponseDTO actualizado = insumosGeneralesService.editarInsumo(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInsumoLogico(@PathVariable Long id) {
        insumosGeneralesService.eliminacionLogica(id);
        return ResponseEntity.noContent().build();
    }
}