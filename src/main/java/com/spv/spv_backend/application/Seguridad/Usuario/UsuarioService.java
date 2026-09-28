package com.spv.spv_backend.application.Seguridad.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;
import com.spv.spv_backend.domain.Seguridad.Rol.Port.RolRepositoryPort;
import com.spv.spv_backend.domain.Seguridad.Usuario.Model.Usuario;
import com.spv.spv_backend.domain.Seguridad.Usuario.Port.UsuarioRepositoryPort;
import com.spv.spv_backend.web.Seguridad.Usuario.DTO.UsuarioRequestDTO;
import com.spv.spv_backend.web.Seguridad.Usuario.DTO.UsuarioResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> obtenerUsuariosActivos() {
        return usuarioRepositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        return usuarioRepositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return mapToResponse(usuario);
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO request) {
        // Validar que el rol exista
        Rol rol = rolRepositoryPort.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getIdRol()));

        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        // Encriptar contraseña antes de guardarla
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(true); // Nace activo por defecto
        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario saved = usuarioRepositoryPort.save(usuario);
        return mapToResponse(saved);
    }

    public UsuarioResponseDTO editarUsuario(Long id, UsuarioRequestDTO request) {
        Usuario existente = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (request.getIdRol() != null) {
            Rol rol = rolRepositoryPort.findById(request.getIdRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getIdRol()));
            existente.setRol(rol);
        }

        existente.setNombre(request.getNombre());
        existente.setCorreo(request.getCorreo());
        
        // Si mandan una nueva contraseña, la encriptamos y actualizamos; si va vacía, la conservamos
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existente.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }

        Usuario updated = usuarioRepositoryPort.save(existente);
        return mapToResponse(updated);
    }

    public UsuarioResponseDTO alternarEstadoUsuario(Long id) {
        Usuario existente = usuarioRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Toggle: Invertir el estado actual
        existente.setEstado(!existente.getEstado());

        Usuario updated = usuarioRepositoryPort.save(existente);
        return mapToResponse(updated);
    }

    private UsuarioResponseDTO mapToResponse(Usuario dom) {
        UsuarioResponseDTO res = new UsuarioResponseDTO();
        res.setIdUsuario(dom.getIdUsuario());
        res.setIdRol(dom.getRol() != null ? dom.getRol().getIdRol() : null);
        res.setNombreRol(dom.getRol() != null ? dom.getRol().getNombre() : null);
        res.setNombre(dom.getNombre());
        res.setCorreo(dom.getCorreo());
        res.setUltimoAcceso(dom.getUltimoAcceso());
        res.setEstado(dom.getEstado());
        res.setFechaCreacion(dom.getFechaCreacion());
        return res;
    }
}