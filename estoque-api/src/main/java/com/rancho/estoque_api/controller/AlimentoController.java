package com.rancho.estoque_api.controller;


import com.rancho.estoque_api.model.Alimento;
import com.rancho.estoque_api.model.TipoDeposito;
import com.rancho.estoque_api.service.AlimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    private final AlimentoService alimentoService;

    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @GetMapping
    public ResponseEntity<List<Alimento>> listarTodos() {
        return ResponseEntity.ok(alimentoService.listarTodos());
    }

    @GetMapping("/deposito/{deposito}")
    public ResponseEntity<List<Alimento>> listarPorDeposito(@PathVariable TipoDeposito deposito) {
        return ResponseEntity.ok(alimentoService.listarPorDeposito(deposito));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alimentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Alimento> cadastrar(@RequestBody Alimento alimento) {
        Alimento novoAlimento = alimentoService.salvar(alimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
