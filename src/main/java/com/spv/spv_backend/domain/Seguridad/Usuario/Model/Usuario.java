package com.spv.spv_backend.domain.Seguridad.Usuario.Model;

import java.time.LocalDateTime;
import com.spv.spv_backend.domain.Seguridad.Rol.Model.Rol;

public class Usuario {

    private Long idUsuario;
    private Rol rol; // Relación con el dominio Rol
    private String nombre;
    private String correo;
    private String passwordHash;
    private LocalDateTime ultimoAcceso;
    private Boolean estado;
    private LocalDateTime fechaCreacion;

    public Usuario() {
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(LocalDateTime ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}