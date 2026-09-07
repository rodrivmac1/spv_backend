package com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Port;

import java.util.List;
import com.spv.spv_backend.domain.GestionInventario.ProduccionSemanalInsumosGenerales.Model.ProduccionSemanalInsumosGenerales;

public interface ProduccionSemanalInsumosGeneralesRepositoryPort {
    List<ProduccionSemanalInsumosGenerales> findByIdProduccionSemanal(Long idProduccionSemanal);
    void saveAll(List<ProduccionSemanalInsumosGenerales> insumos);
}