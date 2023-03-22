package com.starttech.api.de.eventos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "eventos")
@Getter
@Setter
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String local;

    @Future(message = "Data antigas não são permitidas")
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaEvento categoria;

    @Enumerated(EnumType.STRING)
    private StatusEvento status;

    @JsonIgnoreProperties("evento")
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participante> participantes = new ArrayList<>();

}