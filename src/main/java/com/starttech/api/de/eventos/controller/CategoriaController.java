package com.starttech.api.de.eventos.controller;

import com.starttech.api.de.eventos.entity.Categoria;
import com.starttech.api.de.eventos.repository.CategoriaRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepositoy categoriaRepositoy;

    @GetMapping
    public List<Categoria> listar() {
        return (List<Categoria>) categoriaRepositoy.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepositoy.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar(@RequestBody Categoria categoria) {
        return categoriaRepositoy.save(categoria);
    }

}
