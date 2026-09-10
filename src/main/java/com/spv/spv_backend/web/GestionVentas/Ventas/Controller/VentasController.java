package com.spv.spv_backend.web.GestionVentas.Ventas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spv.spv_backend.application.GestionVentas.Ventas.VentasService;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasDetailResponseDTO;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-ventas/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentasController {

    private final VentasService ventasService;

    // GET: Listado general de ventas (cliente, tipo_cliente, fecha, total)
    @GetMapping
    public ResponseEntity<List<VentasListResponseDTO>> listarVentas() {
        return ResponseEntity.ok(ventasService.listarTodasLasVentas());
    }

    // GET por ID: Venta detallada con nombres de presentación y producto
    @GetMapping("/{id}")
    public ResponseEntity<VentasDetailResponseDTO> obtenerVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventasService.obtenerVentaPorId(id));
    }

    // POST: Registro de la venta con su estructura de detalles anidados
    @PostMapping
    public ResponseEntity<VentasDetailResponseDTO> crearVenta(@Valid @RequestBody VentasRequestDTO request) {
        VentasDetailResponseDTO nuevaVenta = ventasService.crearVenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    // DELETE: Borra la venta y sus detalles asociados
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventasService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}