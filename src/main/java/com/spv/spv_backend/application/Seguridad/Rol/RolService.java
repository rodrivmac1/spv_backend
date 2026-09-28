package com.spv.spv_backend.application.Seguridad.Rol;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;
import com.spv.spv_backend.domain.Seguridad.Rol.Port.RolRepositoryPort;
import com.spv.spv_backend.web.Seguridad.Rol.DTO.RolRequestDTO;
import com.spv.spv_backend.web.Seguridad.Rol.DTO.RolResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepositoryPort repositoryPort;

    public List<RolResponseDTO> obtenerRolesActivos() {
        return repositoryPort.listActive().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RolResponseDTO obtenerRolPorId(Long id) {
        Rol rol = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
        return mapToResponse(rol);
    }

    public RolResponseDTO crearRol(RolRequestDTO request) {
        Rol rol = new Rol();
        rol.setNombre(request.getNombre());
        rol.setDescripcion(request.getDescripcion());
        rol.setEstado(true); // Siempre nace activo por defecto

        Rol saved = repositoryPort.save(rol);
        return mapToResponse(saved);
    }

    public RolResponseDTO editarRol(Long id, RolRequestDTO request) {
        Rol existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));

        existente.setNombre(request.getNombre());
        existente.setDescripcion(request.getDescripcion());
        if (request.getEstado() != null) {
            existente.setEstado(request.getEstado());
        }

        Rol updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }


    public RolResponseDTO alternarEstadoRol(Long id) {
        Rol existente = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));

        // Toggle: Si está activo (true) pasa a inactivo (false), y viceversa
        existente.setEstado(!existente.getEstado());
        
        Rol updated = repositoryPort.save(existente);
        return mapToResponse(updated);
    }

    private RolResponseDTO mapToResponse(Rol dom) {
        RolResponseDTO res = new RolResponseDTO();
        res.setIdRol(dom.getIdRol());
        res.setNombre(dom.getNombre());
        res.setDescripcion(dom.getDescripcion());
        res.setEstado(dom.getEstado());
        return res;
    }

    public List<RolResponseDTO> obtenerTodosLosRoles() {
        return repositoryPort.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}