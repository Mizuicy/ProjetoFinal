package com.starttech.api.de.eventos.controller;


import com.starttech.api.de.eventos.entity.Participante;
import com.starttech.api.de.eventos.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Participante")

public class ParticipanteController {
    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping
    public List<Participante> listar() {
        return (List<Participante>) participanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarCPF(@PathVariable Long id) {
        Optional<Participante> participante = participanteRepository.findById(id);

        if (participante.isPresent()) {
            return ResponseEntity.ok(participante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Participante adicionar(@RequestBody Participante participante) {
        return participanteRepository.save(participante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Participante> participante = participanteRepository.findById(id);

        if (participante.isPresent()) {
            participanteRepository.delete(participante.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> atualizar(@PathVariable Long id, @RequestBody Participante participante) {

        if (!participanteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        participante.setId(id);
        Participante participanteAtualizado = participanteRepository.save(participante);
        return ResponseEntity.ok(participanteAtualizado);
    }

}
