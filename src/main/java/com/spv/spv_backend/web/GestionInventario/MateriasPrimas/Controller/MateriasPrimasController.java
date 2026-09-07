package com.spv.spv_backend.web.GestionInventario.MateriasPrimas.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionInventario.MateriasPrimas.MateriasPrimasService;
import com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO.MateriasPrimasRequestDTO;
import com.spv.spv_backend.web.GestionInventario.MateriasPrimas.DTO.MateriasPrimasResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/productos/{idProducto}/materia-prima")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MateriasPrimasController {

    private final MateriasPrimasService materiasPrimasService;

    // GET: Obtener la materia prima de un producto específico
    @GetMapping
    public ResponseEntity<MateriasPrimasResponseDTO> obtenerMateriaPrima(@PathVariable Long idProducto) {
        return ResponseEntity.ok(materiasPrimasService.obtenerPorProducto(idProducto));
    }

    // PUT/POST (Upsert): Crear o actualizar la única materia prima del producto
    @PutMapping
    public ResponseEntity<MateriasPrimasResponseDTO> guardarOActualizarMateriaPrima(
            @PathVariable Long idProducto,
            @Valid @RequestBody MateriasPrimasRequestDTO request) {
        MateriasPrimasResponseDTO response = materiasPrimasService.guardarOActualizar(idProducto, request);
        return ResponseEntity.ok(response);
    }
}