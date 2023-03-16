package com.starttech.api.de.eventos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Participantes")
@Getter
@Setter
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String CPF;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;

    @ManyToMany
    private List<Evento> eventos;
}
