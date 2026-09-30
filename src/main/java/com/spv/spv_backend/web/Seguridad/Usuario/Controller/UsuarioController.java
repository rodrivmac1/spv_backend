package com.spv.spv_backend.web.Seguridad.Usuario.Controller;

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

import com.spv.spv_backend.application.Seguridad.Usuario.UsuarioService;
import com.spv.spv_backend.web.Seguridad.Usuario.DTO.UsuarioRequestDTO;
import com.spv.spv_backend.web.Seguridad.Usuario.DTO.UsuarioResponseDTO;
import com.spv.spv_backend.web.Seguridad.Usuario.DTO.UsuarioUpdateRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seguridad/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosActivos() {
        return ResponseEntity.ok(usuarioService.obtenerUsuariosActivos());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO nuevo = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateRequestDTO request) {
        UsuarioResponseDTO actualizado = usuarioService.editarUsuario(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> alternarEstadoUsuario(@PathVariable Long id) {
        UsuarioResponseDTO actualizado = usuarioService.alternarEstadoUsuario(id);
        return ResponseEntity.ok(actualizado);
    }
}