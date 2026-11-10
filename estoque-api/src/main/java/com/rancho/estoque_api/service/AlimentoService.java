package com.rancho.estoque_api.service;

import com.rancho.estoque_api.model.Alimento;
import com.rancho.estoque_api.model.TipoDeposito;
import com.rancho.estoque_api.repository.AlimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public List<Alimento> listarTodos() {
        return alimentoRepository.findAll();
    }

    public List<Alimento> listarPorDeposito(TipoDeposito deposito) {
        return alimentoRepository.findByTipoDeposito(deposito);
    }

    public Alimento buscarPorId(Long id) {
        return alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado no sistema."));
    }

    public Alimento salvar(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void deletar(Long id) {
        Alimento alimento = buscarPorId(id);
        alimentoRepository.delete(alimento);
    }
}