package com.spv.spv_backend.web.GestionInventario.ProductoInsumo.Controller;

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

import com.spv.spv_backend.application.GestionInventario.ProductoInsumo.ProductoInsumoService;
import com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO.ProductoInsumoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoInsumo.DTO.ProductoInsumoResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/productos/{idProducto}/insumos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoInsumoController {

    private final ProductoInsumoService productoInsumoService;

    // GET: Listar todos los insumos de un producto
    @GetMapping
    public ResponseEntity<List<ProductoInsumoResponseDTO>> listarInsumosPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(productoInsumoService.obtenerInsumosPorProducto(idProducto));
    }

    // POST: Agregar un nuevo insumo a un producto existente
    @PostMapping
    public ResponseEntity<ProductoInsumoResponseDTO> agregarInsumo(
            @PathVariable Long idProducto,
            @Valid @RequestBody ProductoInsumoRequestDTO request) {
        ProductoInsumoResponseDTO nuevoInsumo = productoInsumoService.agregarInsumo(idProducto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInsumo);
    }

    // PUT: Editar un insumo específico del producto
    @PutMapping("/{idInsumo}")
    public ResponseEntity<ProductoInsumoResponseDTO> editarInsumo(
            @PathVariable Long idProducto,
            @PathVariable Long idInsumo,
            @Valid @RequestBody ProductoInsumoRequestDTO request) {
        ProductoInsumoResponseDTO actualizado = productoInsumoService.editarInsumo(idProducto, idInsumo, request);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: Eliminar un insumo específico del producto
    @DeleteMapping("/{idInsumo}")
    public ResponseEntity<Void> eliminarInsumo(
            @PathVariable Long idProducto,
            @PathVariable Long idInsumo) {
        productoInsumoService.eliminarInsumo(idProducto, idInsumo);
        return ResponseEntity.noContent().build();
    }
}