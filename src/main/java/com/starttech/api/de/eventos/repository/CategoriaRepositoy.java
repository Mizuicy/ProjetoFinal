package com.starttech.api.de.eventos.repository;

import com.starttech.api.de.eventos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositoy extends JpaRepository<Categoria, Long> {
}
