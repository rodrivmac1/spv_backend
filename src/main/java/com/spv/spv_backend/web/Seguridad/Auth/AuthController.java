package com.spv.spv_backend.web.Seguridad.Auth;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.spv.spv_backend.infra.Seguridad.Security.JwtService;
import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;
import com.spv.spv_backend.infra.Seguridad.Usuario.Repository.UsuarioJpaRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UsuarioJpaRepository usuarioJpaRepository;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UsuarioJpaRepository usuarioJpaRepository) {

        this.authenticationManager =
                authenticationManager;

        this.jwtService = jwtService;

        this.usuarioJpaRepository =
                usuarioJpaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        log.info("Login: solicitud recibida");

        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNombreUsuario(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException exception) {
            log.warn(
                    "Login: autenticacion rechazada ({})",
                    exception.getClass().getSimpleName());
            throw exception;
        }

        log.info("Login: credenciales aceptadas por Spring Security");

        String nombreUsuario =
                authentication.getName();

        UsuarioEntity usuario =
                usuarioJpaRepository
                        .findByNombreUsuario(nombreUsuario)
                        .orElseThrow();

        log.info("Login: usuario cargado; generando token JWT");
        String token =
                jwtService.generateToken(
                        (org.springframework.security.core.userdetails.UserDetails)
                                authentication.getPrincipal()
                );

        usuario.setUltimoAcceso(LocalDateTime.now());

        usuarioJpaRepository.save(usuario);
        log.info("Login: token generado y ultimo acceso actualizado");

        LoginResponse response =
                new LoginResponse(
                        token,
                        usuario.getNombreUsuario(),
                        usuario.getNombre(),
                        usuario.getRol().getNombre()
                );

        log.info("Login: respuesta exitosa");
        return ResponseEntity.ok(response);
    }
}