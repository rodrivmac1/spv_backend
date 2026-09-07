package com.spv.spv_backend.web.GestionInventario.Producto.Controller;

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

import com.spv.spv_backend.application.GestionInventario.Producto.ProductoService;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoCompletoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoRequestDTO;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-inventario/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    // GET general actualizado que retorna la lista con los conteos y la materia prima
    @GetMapping
    public ResponseEntity<List<ProductoConDetallesResponseDTO>> listarProductosActivos() {
        return ResponseEntity.ok(productoService.obtenerProductosActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProductoCompleto(@Valid @RequestBody ProductoCompletoRequestDTO request) {
        ProductoResponseDTO nuevoProducto = productoService.crearProductoCompleto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> editarProducto(
            @PathVariable Long id, 
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO actualizado = productoService.editarProducto(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductoLogico(@PathVariable Long id) {
        productoService.eliminacionLogica(id);
        return ResponseEntity.noContent().build();
    }
}