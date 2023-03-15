package com.starttech.api.de.eventos.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingresso {
    private long id;

    private String nome;

    private double preco;

    private Integer quantidadeDisponivel;

    private String status;
}
