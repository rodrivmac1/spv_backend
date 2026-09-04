package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity;

import java.util.List;

import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Entity.NumeroSemanaEntity;
import com.spv.spv_backend.infra.GestionInventario.ProduccionProductoPresentacion.Entity.ProduccionProductoPresentacionEntity;
import com.spv.spv_backend.infra.GestionInventario.Producto.Entity.ProductoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produccion_semanal_productos", schema = "public")
@Data
public class ProduccionSemanalProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_producto")
    private Long idProduccionProducto;

    @Column(name = "id_produccion_semanal", nullable = false)
    private Long idProduccionSemanal;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "kg_comprados", nullable = false)
    private Double kgComprados;

    @Column(name = "costo_insumos", nullable = false)
    private Double costoInsumos;

    @Column(name = "costo_general_asignado", nullable = false)
    private Double costoGeneralAsignado;

    @Column(name = "costo_total_lote", nullable = false)
    private Double costoTotalLote;

    @Column(name = "kg_rendimiento", nullable = false)
    private Double kgRendimiento;

    @Column(name = "costo_100g", nullable = false)
    private Double costo100g;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produccion_semanal", referencedColumnName = "id_produccion_semanal", insertable = false, updatable = false)
    private NumeroSemanaEntity produccionSemanal;

    @OneToMany(mappedBy = "produccionSemanalProducto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduccionProductoPresentacionEntity> presentacionesProducto;
}