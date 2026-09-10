package com.spv.spv_backend.infra.GestionVentas.Ventas.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionVentas.Ventas.Entity.VentasEntity;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO;

@Repository
public interface VentasJpaRepository extends JpaRepository<VentasEntity, Long> {

    // Consulta para listar ventas cruzando con clientes y tipo_cliente
    @Query("SELECT new com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO(" +
           "v.idVenta, c.nombre, tc.nombre, v.fecha, v.total) " +
           "FROM VentasEntity v " +
           "JOIN com.spv.spv_backend.infra.GestionVentas.Clientes.Entity.ClientesEntity c ON v.idCliente = c.idCliente " +
           "JOIN com.spv.spv_backend.infra.GestionVentas.TipoCliente.Entity.TipoClienteEntity tc ON c.idTipoCliente = tc.idTipoCliente")
    List<VentasListResponseDTO> findAllVentasConDetalleCliente();
}