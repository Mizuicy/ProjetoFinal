package com.starttech.api.de.eventos.controller;

import com.starttech.api.de.eventos.entity.CategoriaEvento;
import com.starttech.api.de.eventos.entity.Evento;
import com.starttech.api.de.eventos.entity.StatusEvento;
import com.starttech.api.de.eventos.repository.EventoRepository;
import com.starttech.api.de.eventos.repository.ParticipanteRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;
    private ParticipanteRepository participanteRepository;

    @Autowired
    public EventoController(EventoRepository eventoRepository, ParticipanteRepository participanteRepository) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    @GetMapping
    public List<Evento> buscarEventos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(evento.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento, @Valid @RequestParam(value = "status", defaultValue = "DISPONIVEL") String status) {
        evento.setStatus(StatusEvento.valueOf(status.toUpperCase()));
        Evento eventoCriado = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @Valid @RequestBody Evento evento) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);
        if (eventoExistente.isPresent()) {
            evento.setId(id);
            Evento eventoAtualizado = eventoRepository.save(evento);
            return ResponseEntity.status(HttpStatus.OK).body(eventoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deletarEvento(@PathVariable Long id) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);
        if (eventoExistente.isPresent()) {
            eventoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // exemplo de busca localhost:8080/eventos/busca?data=2023-05-01&categoria=TEATRO
    //localhost:8080/eventos/busca?data=2023-05-01 por data
    //localhost:8080/eventos/busca?categoria=TEATRO por categoria
    //localhost:8080/eventos/busca?categoria=FESTA&status=DISPONIVEL por categoria e status
    //localhost:8080/eventos/busca?status=CANCELADO por status
    @GetMapping("/busca")
    public ResponseEntity<List<Evento>> buscarEventosPorDataECategoria(
            @RequestParam(name = "data", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data,
            @RequestParam(name = "categoria", required = false) CategoriaEvento categoria,
            @RequestParam(name = "status", required = false) StatusEvento status) {

        List<Evento> eventosEncontrados;

        if (data != null && categoria != null && status != null) {
            eventosEncontrados = eventoRepository.findByDataAndCategoriaAndStatus(data, categoria, status);
        } else if (data != null && categoria != null) {
            eventosEncontrados = eventoRepository.findByDataAndCategoria(data, categoria);
        } else if (data != null && status != null) {
            eventosEncontrados = eventoRepository.findByDataAndStatus(data, status);
        } else if (categoria != null && status != null) {
            eventosEncontrados = eventoRepository.findByCategoriaAndStatus(categoria, status);
        } else if (data != null) {
            eventosEncontrados = eventoRepository.findByData(data);
        } else if (categoria != null) {
            eventosEncontrados = eventoRepository.findByCategoria(categoria);
        } else if (status != null) {
            eventosEncontrados = eventoRepository.findByStatus(status);
        } else {
            eventosEncontrados = eventoRepository.findAll();
        }

        if (!eventosEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(eventosEncontrados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //http://localhost:8080/eventos/contagem por categoria
    @GetMapping("/contagem")
    public ResponseEntity<Map<CategoriaEvento, Long>> contarEventosPorCategoria(@RequestParam(value = "status", required = false) String status) {
        List<Evento> eventos = eventoRepository.findAll();
        if (status != null) {
            eventos = eventos.stream()
                    .filter(evento -> evento.getStatus().equals(StatusEvento.valueOf(status.toUpperCase())))
                    .collect(Collectors.toList());
        }

        Map<CategoriaEvento, Long> contagemEventos = eventos.stream()
                .collect(Collectors.groupingBy(Evento::getCategoria, Collectors.counting()));

        if (!contagemEventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(contagemEventos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> tratar(ConstraintViolationException exception){
        List<String> erros = new ArrayList<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()){
            String erro = String.format(
                    "%s %s", violation.getPropertyPath().toString(),
                    violation.getMessage());
            erros.add(erro);
        }
        return erros;
    }

}