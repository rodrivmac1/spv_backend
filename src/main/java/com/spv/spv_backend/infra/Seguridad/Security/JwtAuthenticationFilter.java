package com.spv.spv_backend.infra.Seguridad.Security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return "/auth/login".equals(request.getServletPath());
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            log.debug(
                    "JWT: solicitud {} {} sin token Bearer",
                    request.getMethod(),
                    request.getServletPath());
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        String nombreUsuario;

        try {

            nombreUsuario =
                    jwtService.extractUsername(jwt);

        } catch (Exception e) {

            log.warn(
                    "JWT: token invalido para {} {} ({})",
                    request.getMethod(),
                    request.getServletPath(),
                    e.getClass().getSimpleName());
            filterChain.doFilter(request, response);
            return;
        }

        if (nombreUsuario != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(nombreUsuario);

            if (jwtService.isTokenValid(
                    jwt,
                    userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
                log.info(
                        "JWT: token valido; autenticacion establecida para {}",
                        request.getServletPath());
            } else {
                log.warn(
                        "JWT: token no valido para {}",
                        request.getServletPath());
            }
        }

        filterChain.doFilter(request, response);
        if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
            log.warn(
                    "JWT: respuesta 403 en {} {}",
                    request.getMethod(),
                    request.getServletPath());
        }
    }
}