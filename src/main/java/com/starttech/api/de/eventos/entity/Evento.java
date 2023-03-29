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


/*@Entity - é uma notação  do JPA que indica que a class é uma entidade
 * @Table(name = "eventos") -- nome da tabela do banco de dados
 * @Id -- chave primaria da tabela
 *  @GeneratedValue(strategy = GenerationType.IDENTITY) -- indica que o valor do ID é gerado automaticamente
 * */
//@Entity
//@Table(name = "eventos")
//@Getter
//@Setter
//public class Evento {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    @Pattern(regexp = "^[A-Z]+(.)*")
//    private String nomeEvento;
//
//    @NotNull
//    @Pattern(regexp = "^[A-Z]+(.)*")
//    private String descricao;
//
//    @Future(message = "Data antigas não são permitidas")
//    private LocalDate data;
//
//    @NotBlank
//    @Pattern(regexp = "^[A-Z]+(.)*", message = "A primeira letra deve ser maiuscula")
//    private String localizacao;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ingressos")
//    private List<Ingresso> ingressos;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "Convidados")
//    private Categoria categoria;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "evento_participante", joinColumns = @JoinColumn(name = "evento_id"),
//            inverseJoinColumns = @JoinColumn(name = "participante_id")
//    )
//    private List<Participante> participantes;
//}


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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participante> participantes = new ArrayList<>();

}
