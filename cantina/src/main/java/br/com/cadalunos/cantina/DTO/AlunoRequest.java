package br.com.cadalunos.cantina.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoRequest {

    private String nome;
    private String sexo;
    private int anoNascimento;
}