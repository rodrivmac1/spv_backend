package com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Entity;

import com.spv.spv_backend.infra.GestionInventario.InsumosGenerales.Entity.InsumosGeneralesEntity;
import com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Entity.NumeroSemanaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produccion_semanal_insumos_generales", schema = "public")
@Data
public class ProduccionSemanalInsumosGeneralesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_semanal_insumo_general")
    private Long idProduccionSemanalInsumoGeneral;

    @Column(name = "id_produccion_semanal", nullable = false)
    private Long idProduccionSemanal;

    @Column(name = "id_insumo_general", nullable = false)
    private Long idInsumoGeneral;

    @Column(name = "costo", nullable = false)
    private Double costo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produccion_semanal", referencedColumnName = "id_produccion_semanal", insertable = false, updatable = false)
    private NumeroSemanaEntity produccionSemanal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insumo_general", referencedColumnName = "id_insumo_general", insertable = false, updatable = false)
    private InsumosGeneralesEntity insumoGeneral;
}