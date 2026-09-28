package com.spv.spv_backend.infra.Seguridad.Usuario.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.Seguridad.Usuario.Entity.UsuarioEntity;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @EntityGraph(attributePaths = "rol")
    List<UsuarioEntity> findByEstadoTrue();

    @EntityGraph(attributePaths = "rol")
    List<UsuarioEntity> findAll();

    @EntityGraph(attributePaths = "rol")
    Optional<UsuarioEntity> findById(Long id);

    @EntityGraph(attributePaths = "rol")
    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);
}