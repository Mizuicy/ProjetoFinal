package com.starttech.api.de.eventos.repository;


import com.starttech.api.de.eventos.entity.Evento;
import com.starttech.api.de.eventos.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findByEvento(Evento evento);

    void deleteById(Long id);

    Participante save(Participante participante);
}

