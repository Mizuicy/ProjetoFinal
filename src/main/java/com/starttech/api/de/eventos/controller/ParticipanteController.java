package com.starttech.api.de.eventos.controller;



import com.starttech.api.de.eventos.entity.Evento;
import com.starttech.api.de.eventos.entity.Participante;
import com.starttech.api.de.eventos.repository.exceptionhandler.ResourceNotFoundException;
import com.starttech.api.de.eventos.repository.EventoRepository;
import com.starttech.api.de.eventos.repository.ParticipanteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/participante")
@Validated
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Participante> buscarEventos() {
        return participanteRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Participante> criarParticipante(@Valid @RequestBody Participante participante) {
        Participante novoParticipante = participanteRepository.saveAndFlush(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoParticipante);
    }
    //localhost:8080/participantes/1/adicionarEvento/1
    @PostMapping("/eventos/{eventoId}/participantes/{participanteId}")
    public ResponseEntity<Evento> adicionarEvento(@PathVariable Long eventoId, @PathVariable Long participanteId) {
        Optional<Evento> optionalEvento = eventoRepository.findById(eventoId);
        Optional<Participante> optionalParticipante = participanteRepository.findById(participanteId);

        if (optionalEvento.isEmpty()) {
            throw new ResourceNotFoundException("Evento não encontrado");
        }

        if (optionalParticipante.isEmpty()) {
            throw new ResourceNotFoundException("Participante não encontrado");
        }

        Evento evento = optionalEvento.get();
        Participante participante = optionalParticipante.get();

        if (evento.getParticipantes().contains(participante)) {
            throw new IllegalArgumentException("O participante já está cadastrado no evento");
        }

        evento.getParticipantes().add(participante);
        participante.setEvento(evento);

        eventoRepository.save(evento);

        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Participante> atualizarParticipante(@PathVariable("id") @NotNull Long id, @Valid @RequestBody Participante participante) {
        Participante participanteAtualizado = participanteRepository.findById(id).map(p -> {
            p.setNome(participante.getNome());
            p.setEmail(participante.getEmail());
            return participanteRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado com o ID: " + id));
        return ResponseEntity.ok().body(participanteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Participante>> excluirParticipante(@PathVariable("id") @NotNull Long id) {
        participanteRepository.deleteById(id);
        List<Participante> participantes = participanteRepository.findAll();
        return ResponseEntity.ok().body(participantes);
    }
}


