package com.starttech.api.de.eventos.repository;

import com.starttech.api.de.eventos.entity.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {

}
