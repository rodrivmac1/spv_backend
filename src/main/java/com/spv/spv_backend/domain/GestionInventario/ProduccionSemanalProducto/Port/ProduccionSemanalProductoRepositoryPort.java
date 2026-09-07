package com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Port;

import java.util.List;
import java.util.Optional;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalProducto.Model.ProduccionSemanalProducto;

public interface ProduccionSemanalProductoRepositoryPort {
    List<ProduccionSemanalProducto> findByIdProduccionSemanal(Long idProduccionSemanal);
    Optional<ProduccionSemanalProducto> findById(Long id);
    ProduccionSemanalProducto save(ProduccionSemanalProducto producto);
    List<ProduccionSemanalProducto> saveAll(List<ProduccionSemanalProducto> productos);
}