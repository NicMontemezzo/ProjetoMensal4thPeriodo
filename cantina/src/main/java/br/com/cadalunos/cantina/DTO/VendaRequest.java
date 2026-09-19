package br.com.cadalunos.cantina.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendaRequest {

    @NotNull(message = "Aluno é obrigatório")
    private Long alunoId;

    @NotEmpty(message = "A venda precisa ter ao menos um item")
    @Valid
    private List<ItemVendaRequest> itens;
}