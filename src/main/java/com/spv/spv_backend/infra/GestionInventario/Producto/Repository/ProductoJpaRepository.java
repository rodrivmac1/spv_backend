package com.spv.spv_backend.infra.GestionInventario.Producto.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spv.spv_backend.infra.GestionInventario.Producto.Entity.ProductoEntity;
import com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
    
    List<ProductoEntity> findByEstadoTrue();

    @Query("SELECT new com.spv.spv_backend.web.GestionInventario.Producto.DTO.ProductoConDetallesResponseDTO(" +
           "p.idProducto, p.nombre, p.estado, " +
           "COALESCE(mp.nombre, 'Sin Materia Prima'), " +
           "COUNT(DISTINCT i.idProductoInsumo), " +
           "COUNT(DISTINCT pr.idProductoPresentacion), " +
           "p.fechaCreacion) " + // <-- Añadido aquí
           "FROM ProductoEntity p " +
           "LEFT JOIN MateriasPrimasEntity mp ON mp.idProducto = p.idProducto " +
           "LEFT JOIN ProductoInsumoEntity i ON i.idProducto = p.idProducto " +
           "LEFT JOIN ProductoPresentacionEntity pr ON pr.idProducto = p.idProducto " +
           "WHERE p.estado = true " +
           "GROUP BY p.idProducto, p.nombre, p.estado, p.fechaCreacion, mp.nombre")
    List<ProductoConDetallesResponseDTO> listarProductosConDetallesActivos();
}