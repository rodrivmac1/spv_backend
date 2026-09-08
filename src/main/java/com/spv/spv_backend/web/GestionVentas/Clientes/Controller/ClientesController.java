package com.spv.spv_backend.web.GestionVentas.Clientes.Controller;

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

import com.spv.spv_backend.application.GestionVentas.Clientes.ClientesService;
import com.spv.spv_backend.web.GestionVentas.Clientes.DTO.ClientesRequestDTO;
import com.spv.spv_backend.web.GestionVentas.Clientes.DTO.ClientesResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-ventas/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientesController {

    private final ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarClientesActivos() {
        return ResponseEntity.ok(clientesService.obtenerClientesActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.obtenerClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<ClientesResponseDTO> crearCliente(@Valid @RequestBody ClientesRequestDTO request) {
        ClientesResponseDTO nuevo = clientesService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> editarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClientesRequestDTO request) {
        ClientesResponseDTO actualizado = clientesService.editarCliente(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> eliminarOActivarCliente(@PathVariable Long id) {
        ClientesResponseDTO clienteActualizado = clientesService.alternarEstadoCliente(id);
        return ResponseEntity.ok(clienteActualizado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientesResponseDTO>> listarTodosLosClientes() {
        return ResponseEntity.ok(clientesService.obtenerTodosLosClientes());
    }
}