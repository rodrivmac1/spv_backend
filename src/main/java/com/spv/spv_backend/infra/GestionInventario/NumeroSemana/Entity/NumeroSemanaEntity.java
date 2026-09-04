package com.spv.spv_backend.infra.GestionInventario.NumeroSemana.Entity;

import java.time.LocalDate;
import java.util.List;

import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalInsumosGenerales.Entity.ProduccionSemanalInsumosGeneralesEntity;
import com.spv.spv_backend.infra.GestionInventario.ProduccionSemanalProducto.Entity.ProduccionSemanalProductoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produccion_semanal", schema = "public")
@Data
public class NumeroSemanaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_semanal")
    private Long idProduccionSemanal;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "numero_semana", nullable = false)
    private Integer numeroSemana;

    @OneToMany(mappedBy = "produccionSemanal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduccionSemanalProductoEntity> productosSemanal;

    @OneToMany(mappedBy = "produccionSemanal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduccionSemanalInsumosGeneralesEntity> insumosGeneralesSemanal;
}