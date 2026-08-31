package com.spv.spv_backend.infra.GestionInventario.MateriasPrimas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "materias_primas", schema = "public")
@Data
public class MateriasPrimasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia_prima")
    private Long idMateriaPrima;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "kg_comprados", nullable = false)
    private Double kgComprados;

    @Column(name = "kg_rendimiento", nullable = false)
    private Double kgRendimiento;
}