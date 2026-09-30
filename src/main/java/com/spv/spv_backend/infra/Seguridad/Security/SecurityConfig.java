package com.spv.spv_backend.infra.Seguridad.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Imports necesarios para CORS:
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
public class SecurityConfig {

    private static final Logger log =
            LoggerFactory.getLogger(SecurityConfig.class);

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter>
            jwtAuthenticationFilterRegistration(
                    JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
            // Habilitar CORS
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())

            // API stateless
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            // Permisos
            .authorizeHttpRequests(auth -> auth
                    // Permitir solicitudes OPTIONS para que CORS funcione correctamente
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // Login público
                    .requestMatchers(
                            HttpMethod.POST,
                            "/auth/login"
                    )
                    .permitAll()

                    // Swagger si posteriormente lo utilizas
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    )
                    .permitAll()

                    // Lecturas generales y registro de ventas para usuarios.
                    .requestMatchers(
                            HttpMethod.POST,
                            "/gestion-ventas/ventas"
                    )
                    .hasAnyRole("Administrador", "Usuario")
                    .requestMatchers(
                            HttpMethod.DELETE,
                            "/gestion-ventas/ventas/**"
                    )
                    .hasAnyRole("Administrador", "Usuario")
                    .requestMatchers(HttpMethod.GET, "/**")
                    .hasAnyRole("Administrador", "Usuario")

                    // El resto de la aplicación es exclusivo de administradores
                    .anyRequest()
                    .hasRole("Administrador")
            )
            .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint((request, response, exception) -> {
                        log.warn(
                                "Seguridad: solicitud sin autenticar rechazada: {} {}",
                                request.getMethod(),
                                request.getServletPath());
                        response.sendError(
                                HttpStatus.UNAUTHORIZED.value());
                    })
                    .accessDeniedHandler((request, response, exception) -> {
                        log.warn(
                                "Seguridad: acceso prohibido a {} {} ({})",
                                request.getMethod(),
                                request.getServletPath(),
                                exception.getMessage());
                        response.sendError(
                                HttpStatus.FORBIDDEN.value());
                    })
            )

            // JWT antes del filtro de usuario/password
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    // =========================================================================
    // CONFIGURACIÓN GLOBAL DE CORS (Agregado aquí al final de SecurityConfig)
    // =========================================================================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // URL de tu frontend con Vite (cambia el puerto si tu front usa otro)
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitir cualquier cabecera (incluyendo Authorization para los JWT)
        configuration.setAllowedHeaders(List.of("*"));
        
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}