package com.starttech.api.de.eventos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


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
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nomeEvento;

    @NotNull
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String descricao;

    @Future(message = "Data antigas não são permitidas")
    private LocalDate data;

    @NotBlank
    @Pattern(regexp = "^[A-Z]+(.)*", message = "A primeira letra deve ser maiuscula")
    private String localizacao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingressos")
    private List<Ingresso> ingressos;
    @ManyToOne
    private Categoria categoria;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evento_participante", joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantes;

//    @ManyToMany
//    private List<Participante> participante;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
//    private List<Aluno> alunos;
}