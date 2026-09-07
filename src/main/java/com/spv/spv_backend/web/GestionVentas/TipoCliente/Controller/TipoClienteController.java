package com.spv.spv_backend.web.GestionVentas.TipoCliente.Controller;

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

import com.spv.spv_backend.application.GestionVentas.TipoCliente.TipoClienteService;
import com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO.TipoClienteRequestDTO;
import com.spv.spv_backend.web.GestionVentas.TipoCliente.DTO.TipoClienteResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gestion-ventas/tipo-cliente")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TipoClienteController {

    private final TipoClienteService tipoClienteService;

    @GetMapping
    public ResponseEntity<List<TipoClienteResponseDTO>> listarTiposClientesActivos() {
        return ResponseEntity.ok(tipoClienteService.obtenerTiposClientesActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoClienteResponseDTO> obtenerTipoClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoClienteService.obtenerTipoClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoClienteResponseDTO> crearTipoCliente(@Valid @RequestBody TipoClienteRequestDTO request) {
        TipoClienteResponseDTO nuevo = tipoClienteService.crearTipoCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoClienteResponseDTO> editarTipoCliente(
            @PathVariable Long id,
            @Valid @RequestBody TipoClienteRequestDTO request) {
        TipoClienteResponseDTO actualizado = tipoClienteService.editarTipoCliente(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoClienteLogico(@PathVariable Long id) {
        tipoClienteService.eliminacionLogica(id);
        return ResponseEntity.noContent().build();
    }
}