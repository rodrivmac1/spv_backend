package com.spv.spv_backend.web.Seguridad.Rol.Controller;

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

import com.spv.spv_backend.application.Seguridad.Rol.RolService;
import com.spv.spv_backend.web.Seguridad.Rol.DTO.RolRequestDTO;
import com.spv.spv_backend.web.Seguridad.Rol.DTO.RolResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seguridad/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> listarRolesActivos() {
        return ResponseEntity.ok(rolService.obtenerRolesActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> obtenerRolPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.obtenerRolPorId(id));
    }

    @PostMapping
    public ResponseEntity<RolResponseDTO> crearRol(@Valid @RequestBody RolRequestDTO request) {
        RolResponseDTO nuevo = rolService.crearRol(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> editarRol(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDTO request) {
        RolResponseDTO actualizado = rolService.editarRol(id, request);
        return ResponseEntity.ok(actualizado);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<RolResponseDTO> alternarEstadoRol(@PathVariable Long id) {
        RolResponseDTO actualizado = rolService.alternarEstadoRol(id);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<RolResponseDTO>> listarTodosLosRoles() {
        return ResponseEntity.ok(rolService.obtenerTodosLosRoles());
    }
}