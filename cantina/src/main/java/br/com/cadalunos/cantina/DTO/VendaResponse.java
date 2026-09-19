package br.com.cadalunos.cantina.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VendaResponse {
    private Long id;
    private Long alunoId;
    private String alunoNome;
    private LocalDateTime dataVenda;
    private BigDecimal total;
    private List<ItemVendaResponse> itens;
}