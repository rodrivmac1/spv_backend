package com.spv.spv_backend.infra.GestionVentas.Clientes.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionVentas.Clientes.Entity.ClientesEntity;

@Repository
public interface ClientesJpaRepository extends JpaRepository<ClientesEntity, Long> {
    List<ClientesEntity> findByEstadoTrue();
}