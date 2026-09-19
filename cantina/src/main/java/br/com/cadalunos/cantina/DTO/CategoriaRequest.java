package br.com.cadalunos.cantina.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;
}