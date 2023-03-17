package com.starttech.api.de.eventos.repository;

import com.starttech.api.de.eventos.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

}
