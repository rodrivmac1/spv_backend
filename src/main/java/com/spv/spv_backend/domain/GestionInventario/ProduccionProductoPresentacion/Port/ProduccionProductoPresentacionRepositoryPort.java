package com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Port;

import java.util.List;
import com.spv.spv_backend.domain.GestionInventario.ProduccionProductoPresentacion.Model.ProduccionProductoPresentacion;

public interface ProduccionProductoPresentacionRepositoryPort {
    List<ProduccionProductoPresentacion> saveAll(List<ProduccionProductoPresentacion> lista);
    List<ProduccionProductoPresentacion> findByIdProduccionProducto(Long idProduccionProducto);
    List<ProduccionProductoPresentacion> findByIdProduccionProductoAndIdProduccionSemanal(
            Long idProduccionProducto, Long idProduccionSemanal);
}