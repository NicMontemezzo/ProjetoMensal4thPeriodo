package br.com.cadalunos.cantina.Service;

import br.com.cadalunos.cantina.DTO.*;
import br.com.cadalunos.cantina.Entity.*;
import br.com.cadalunos.cantina.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final AlunoRepository alunoRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public VendaResponse registrarVenda(VendaRequest request) {
        log.info("Registrando venda para aluno {}", request.getAlunoId());

        Aluno aluno = alunoRepository.findById(request.getAlunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        Venda venda = new Venda();
        venda.setAluno(aluno);

        BigDecimal total = BigDecimal.ZERO;
        List<ItemVenda> itensVenda = new ArrayList<>();

        for (ItemVendaRequest itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Produto não encontrado: " + itemRequest.getProdutoId()));

            if (produto.getEstoque() < itemRequest.getQuantidade()) {
                log.error("Estoque insuficiente para produto {}: disponível {}, solicitado {}",
                        produto.getNome(), produto.getEstoque(), itemRequest.getQuantidade());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Baixa o estoque
            produto.setEstoque(produto.getEstoque() - itemRequest.getQuantidade());
            produtoRepository.save(produto);

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemRequest.getQuantidade()));
            total = total.add(subtotal);

            itensVenda.add(item);
        }

        venda.setItens(itensVenda);
        venda.setTotal(total);

        Venda salva = vendaRepository.save(venda);
        log.info("Venda {} registrada com sucesso. Total: {}", salva.getId(), total);

        return toResponse(salva);
    }

    public VendaResponse findById(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
        return toResponse(venda);
    }

    public List<VendaResponse> listar() {
        return vendaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private VendaResponse toResponse(Venda venda) {
        List<ItemVendaResponse> itens = venda.getItens().stream()
                .map(item -> new ItemVendaResponse(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                ))
                .toList();

        return new VendaResponse(
                venda.getId(),
                venda.getAluno().getId(),
                venda.getAluno().getNome(),
                venda.getDataVenda(),
                venda.getTotal(),
                itens
        );
    }
}