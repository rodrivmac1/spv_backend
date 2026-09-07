package com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.Controller;

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

import com.spv.spv_backend.application.GestionInventario.ProductoPresentacion.ProductoPresentacionService;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionRequestDTO;
import com.spv.spv_backend.web.GestionInventario.ProductoPresentacion.DTO.ProductoPresentacionResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/productos/{idProducto}/presentaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoPresentacionController {

    private final ProductoPresentacionService productoPresentacionService;

    // GET: Listar todas las presentaciones de un producto
    @GetMapping
    public ResponseEntity<List<ProductoPresentacionResponseDTO>> listarPresentacionesPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(productoPresentacionService.obtenerPresentacionesPorProducto(idProducto));
    }

    // POST: Agregar una nueva presentación a un producto existente
    @PostMapping
    public ResponseEntity<ProductoPresentacionResponseDTO> agregarPresentacion(
            @PathVariable Long idProducto,
            @Valid @RequestBody ProductoPresentacionRequestDTO request) {
        ProductoPresentacionResponseDTO nuevaPresentacion = productoPresentacionService.agregarPresentacion(idProducto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPresentacion);
    }

    // PUT: Editar una presentación específica del producto
    @PutMapping("/{idPresentacion}")
    public ResponseEntity<ProductoPresentacionResponseDTO> editarPresentacion(
            @PathVariable Long idProducto,
            @PathVariable Long idPresentacion,
            @Valid @RequestBody ProductoPresentacionRequestDTO request) {
        ProductoPresentacionResponseDTO actualizado = productoPresentacionService.editarPresentacion(idProducto, idPresentacion, request);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: Eliminar una presentación específica del producto
    @DeleteMapping("/{idPresentacion}")
    public ResponseEntity<Void> eliminarPresentacion(
            @PathVariable Long idProducto,
            @PathVariable Long idPresentacion) {
        productoPresentacionService.eliminarPresentacion(idProducto, idPresentacion);
        return ResponseEntity.noContent().build();
    }
}