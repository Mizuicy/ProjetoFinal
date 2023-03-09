package com.starttech.api.de.eventos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
@Getter
@Setter
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Evento> listar() {
        return eventoRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscar(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento adicionar(@RequestBody Evento evento) {

        return eventoRepository.save(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isPresent()) {
            eventoRepository.delete(evento.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
        public ResponseEntity<Evento> atualizar(@PathVariable Long id , @Valid @RequestBody Evento evento) {

        if (!eventoRepository.existsById(id)){
            return ResponseEntity.notFound().build();

            }
        evento.setId(id);
        Evento eventoAtualizado = eventoRepository.save(evento);
        return ResponseEntity.ok(eventoAtualizado);
        }
    }





