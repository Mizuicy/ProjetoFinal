package com.starttech.api.de.eventos.controller;

import com.starttech.api.de.eventos.entity.Ingresso;
import com.starttech.api.de.eventos.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoRepository ingressoRepository;

    @GetMapping
    public List<Ingresso> listarIngressos() {
        return (List<Ingresso>) ingressoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> buscarIngresso(@PathVariable long id) {
        Optional<Ingresso> ingresso = ingressoRepository.findById(id);
        if (ingresso.isPresent()) {
            return ResponseEntity.ok(ingresso.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Ingresso criarIngresso(@RequestBody Ingresso ingresso) {
        ingresso.setStatus("disponivel"); // setando o status para disponivel quando for criado
        return ingressoRepository.save(ingresso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable long id, @RequestBody Ingresso ingresso) {
        if (ingressoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingresso.setId(id);
        Ingresso ingressoAtualizado = ingressoRepository.save(ingresso);
        return ResponseEntity.ok(ingressoAtualizado);
    }


}
