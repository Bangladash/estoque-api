package com.rancho.estoque_api.repository;

import com.rancho.estoque_api.model.Alimento;
import com.rancho.estoque_api.model.TipoDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento,Long> {
    List<Alimento> findByTipoDeposito(TipoDeposito tipoDeposito);
}
