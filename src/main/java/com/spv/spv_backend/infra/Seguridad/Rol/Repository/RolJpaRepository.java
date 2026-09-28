package com.spv.spv_backend.infra.Seguridad.Rol.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.Seguridad.Rol.Entity.RolEntity;

@Repository
public interface RolJpaRepository extends JpaRepository<RolEntity, Long> {
    List<RolEntity> findByEstadoTrue();
}