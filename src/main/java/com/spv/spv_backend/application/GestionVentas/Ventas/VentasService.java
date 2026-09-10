package com.spv.spv_backend.application.GestionVentas.Ventas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spv.spv_backend.domain.GestionVentas.Ventas.Model.Ventas;
import com.spv.spv_backend.domain.GestionVentas.Ventas.Port.VentasRepositoryPort;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasDetailResponseDTO;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasListResponseDTO;
import com.spv.spv_backend.web.GestionVentas.Ventas.DTO.VentasRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentasService {

    private final VentasRepositoryPort ventasRepositoryPort;
    private final JdbcTemplate jdbcTemplate;

    // POST: Guarda la venta y sus detalles de forma atómica
    @Transactional
    public VentasDetailResponseDTO crearVenta(VentasRequestDTO request) {
        Ventas ventaDomain = new Ventas();
        ventaDomain.setIdCliente(request.getIdCliente());
        
        // Si el front no manda fecha, asignamos la actual para evitar nulos
        ventaDomain.setFecha(request.getFecha() != null ? request.getFecha() : LocalDateTime.now());
        ventaDomain.setTotal(request.getTotal());

        List<Ventas.VentasDetalleDomain> detallesDomain = request.getDetalles().stream().map(d -> {
            Ventas.VentasDetalleDomain det = new Ventas.VentasDetalleDomain();
            det.setIdProductoPresentacion(d.getIdProductoPresentacion());
            det.setCantidad(d.getCantidad());
            det.setPrecioUnitario(d.getPrecioUnitario());
            det.setSubtotal(d.getSubtotal());
            return det;
        }).collect(Collectors.toList());

        ventaDomain.setDetalles(detallesDomain);

        Ventas saved = ventasRepositoryPort.save(ventaDomain);
        return obtenerVentaPorId(saved.getIdVenta());
    }

    // GET por ID: Obtiene la venta y sus detalles con JdbcTemplate
    public VentasDetailResponseDTO obtenerVentaPorId(Long idVenta) {
        String sqlVenta = "SELECT id_venta, id_cliente, fecha, total FROM ventas WHERE id_venta = ?";
        
        VentasDetailResponseDTO ventaDto = jdbcTemplate.queryForObject(sqlVenta, (rs, rowNum) -> {
            VentasDetailResponseDTO v = new VentasDetailResponseDTO();
            v.setIdVenta(rs.getLong("id_venta"));
            v.setIdCliente(rs.getLong("id_cliente"));
            v.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            v.setTotal(rs.getDouble("total"));
            return v;
        }, idVenta);

        String sqlDetalles = "SELECT vd.id_venta_detalle, vd.id_producto_presentacion, vd.cantidad, " +
                             "vd.precio_unitario, vd.subtotal, pp.nombre AS nombre_presentacion, p.nombre AS nombre_producto " +
                             "FROM venta_detalle vd " +
                             "JOIN producto_presentaciones pp ON vd.id_producto_presentacion = pp.id_producto_presentacion " +
                             "JOIN productos p ON pp.id_producto = p.id_producto " +
                             "WHERE vd.id_venta = ?";

        List<VentasDetailResponseDTO.DetalleItemDTO> detalles = jdbcTemplate.query(sqlDetalles, (rs, rowNum) -> {
            VentasDetailResponseDTO.DetalleItemDTO item = new VentasDetailResponseDTO.DetalleItemDTO();
            item.setIdVentaDetalle(rs.getLong("id_venta_detalle"));
            item.setIdProductoPresentacion(rs.getLong("id_producto_presentacion"));
            item.setCantidad(rs.getInt("cantidad"));
            item.setPrecioUnitario(rs.getDouble("precio_unitario"));
            item.setSubtotal(rs.getDouble("subtotal"));
            item.setNombrePresentacion(rs.getString("nombre_presentacion"));
            item.setNombreProducto(rs.getString("nombre_producto"));
            return item;
        }, idVenta);

        ventaDto.setDetalles(detalles);
        return ventaDto;
    }

    // GET Listado general
    public List<VentasListResponseDTO> listarTodasLasVentas() {
        return ventasRepositoryPort.findAllListado();
    }

    // DELETE: Borra la venta y sus detalles en cascada
    @Transactional
    public void eliminarVenta(Long idVenta) {
        ventasRepositoryPort.findByIdWithDetails(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + idVenta));
        ventasRepositoryPort.deleteById(idVenta);
    }
}