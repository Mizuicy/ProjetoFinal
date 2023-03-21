package com.starttech.api.de.eventos.repository;

import com.starttech.api.de.eventos.entity.CategoriaEvento;
import com.starttech.api.de.eventos.entity.Evento;
import com.starttech.api.de.eventos.entity.StatusEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // buscar eventos por data
    List<Evento> findByData(LocalDate data);

    // buscar eventos por categoria
    List<Evento> findByCategoria(CategoriaEvento categoria);

    // buscar eventos por data e categoria
    List<Evento> findByDataAndCategoria(LocalDate data, CategoriaEvento categoria);

    //Contagem de categorias
    @Query("SELECT e.categoria, COUNT(e) FROM Evento e GROUP BY e.categoria")
    List<Object[]> contarEventosPorCategoria();


    List<Evento> findByDataAndCategoriaAndStatus(LocalDate data, CategoriaEvento categoria, StatusEvento status);

    List<Evento> findByDataAndStatus(LocalDate data, StatusEvento status);

    List<Evento> findByCategoriaAndStatus(CategoriaEvento categoria, StatusEvento status);

    List<Evento> findByStatus(StatusEvento status);
}
