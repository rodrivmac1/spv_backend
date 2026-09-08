package com.spv.spv_backend.infra.GestionVentas.Clientes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clientes", schema = "public")
@Data
public class ClientesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "id_tipo_cliente", nullable = false)
    private Long idTipoCliente;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}