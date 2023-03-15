package com.starttech.api.de.eventos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/*@Entity - é uma notação  do JPA que indica que a class é uma entidade
* @Table(name = "eventos") -- nome da tabela do banco de dados
* @Id -- chave primaria da tabela
*  @GeneratedValue(strategy = GenerationType.IDENTITY) -- indica que o valor do ID é gerado automaticamente
* */
@Entity
@Table(name = "eventos")
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nome;

    private String descricao;

    @Future(message = "Data antigas não são permitidas" )
    private LocalDate data;

    @NotBlank
    private String localizacao;

    @NotBlank
    private String categoria;

    @ManyToOne
    private Participante participante;

}