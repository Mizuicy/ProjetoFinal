package com.starttech.api.de.eventos.repository;

import com.starttech.api.de.eventos.entity.Participante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {

}
