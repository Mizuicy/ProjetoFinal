package com.starttech.api.de.eventos.Service;

import com.starttech.api.de.eventos.entity.Evento;
import com.starttech.api.de.eventos.entity.Participante;
import com.starttech.api.de.eventos.repository.CategoriaRepositoy;
import com.starttech.api.de.eventos.repository.EventoRepository;
import com.starttech.api.de.eventos.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private CategoriaRepositoy categoriaRepository;

    public void adicionarParticipante(Long eventoId, Long participanteId) {
        Evento evento = eventoRepository.findById(eventoId).orElseThrow();
        Participante participante = participanteRepository.findById(participanteId).orElseThrow();

        evento.getParticipantes().add(participante);
        participante.getEventos().add(evento);

        eventoRepository.save(evento);
        participanteRepository.save(participante);
    }

//    public void associarCategoriaEvento(Long eventoId, Long categoriaEventoId) {
//        Evento evento = eventoRepository.findById(eventoId).orElseThrow();
//        Categoria categoria = categoriaRepository.findById(categoriaEventoId).orElseThrow();
//
//        evento.setCategoria(categoria);
//
//        eventoRepository.save(evento);
//    }
}
