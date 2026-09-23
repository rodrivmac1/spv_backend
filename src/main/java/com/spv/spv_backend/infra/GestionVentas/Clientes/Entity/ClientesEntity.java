package com.spv.spv_backend.infra.GestionVentas.Clientes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.spv.spv_backend.infra.GestionVentas.TipoCliente.Entity.TipoClienteEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_cliente", insertable = false, updatable = false)
    private TipoClienteEntity tipoCliente;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}