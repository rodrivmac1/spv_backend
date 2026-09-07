package com.spv.spv_backend.infra.GestionVentas.TipoCliente.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionVentas.TipoCliente.Entity.TipoClienteEntity;

@Repository
public interface TipoClienteJpaRepository extends JpaRepository<TipoClienteEntity, Long> {
    List<TipoClienteEntity> findByEstadoTrue();
}