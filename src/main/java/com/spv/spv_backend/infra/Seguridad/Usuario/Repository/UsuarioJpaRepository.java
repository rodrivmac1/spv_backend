package com.spv.spv_backend.infra.Seguridad.Usuario.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByEstadoTrue();
    Optional<UsuarioEntity> findByCorreo(String correo);
}