package com.spv.spv_backend.infra.Seguridad.Security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;
import com.spv.spv_backend.infra.Seguridad.Usuario.Repository.UsuarioJpaRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log =
            LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UsuarioJpaRepository usuarioJpaRepository;

    public CustomUserDetailsService(
            UsuarioJpaRepository usuarioJpaRepository) {

        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario)
            throws UsernameNotFoundException {

        log.info("Autenticacion: buscando usuario en base de datos");

        UsuarioEntity usuario = usuarioJpaRepository
                .findByNombreUsuario(nombreUsuario)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado"));

        log.info(
                "Autenticacion: usuario encontrado; activo={}, rol={}",
                usuario.getEstado(),
                usuario.getRol().getNombre());

        if (usuario.getEstado() == null) {
            log.error("Autenticacion: el estado del usuario es null");
            throw new IllegalStateException(
                    "El estado del usuario no puede ser null");
        }

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getPasswordHash())
                .authorities(
                        new SimpleGrantedAuthority(
                                "ROLE_" + usuario.getRol().getNombre()
                        )
                )
                .disabled(!usuario.getEstado())
                .build();
    }
}