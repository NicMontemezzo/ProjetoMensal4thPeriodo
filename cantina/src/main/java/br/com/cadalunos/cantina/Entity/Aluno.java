package br.com.cadalunos.cantina.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sexo;
    private int anoNascimento;

    @Column(nullable = false, unique = true, length = 20)
    private String matricula;

    private String turma;
}