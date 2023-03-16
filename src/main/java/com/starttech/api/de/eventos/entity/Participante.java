package com.starttech.api.de.eventos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table(name = "Participantes")
@Getter
@Setter
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF(message = "cpf inválido")
    private String CPF;

    @NotBlank(message = "O nome é obrigatório") //Não permite valor nulo e o comprimento "espaços em branco"
    @Pattern(regexp = "^[A-Z]+(.)*")// O @Pattern é Validação para primeira letra ser maiuscula
    @Length(min = 3, max = 35, message = "O nome deverá ter no máximo {max} caracteres")
    private String nomeParticipante;

    @Email
    private String email;

    @ManyToMany //Esta anotação define uma associação com outra entidade
    private List<Evento> eventos;
}
