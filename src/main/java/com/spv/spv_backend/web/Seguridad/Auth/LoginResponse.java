package com.spv.spv_backend.web.Seguridad.Auth;

public class LoginResponse {

    private String token;

    private String nombreUsuario;

    private String nombre;

    private String rol;

    public LoginResponse(
            String token,
            String nombreUsuario,
            String nombre,
            String rol) {

        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }
}