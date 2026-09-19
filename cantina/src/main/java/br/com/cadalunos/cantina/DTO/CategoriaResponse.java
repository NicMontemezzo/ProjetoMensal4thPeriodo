package br.com.cadalunos.cantina.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaResponse {
    private Long id;
    private String nome;
    private String descricao;
}