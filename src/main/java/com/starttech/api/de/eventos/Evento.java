package com.starttech.api.de.eventos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull
    private Long id;

    @NotNull
    private String nome;

    private String descricao;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull
    private String localizacao;

    @NotNull
    private String categoria;

}