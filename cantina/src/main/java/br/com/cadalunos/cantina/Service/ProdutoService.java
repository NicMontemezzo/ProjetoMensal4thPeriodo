package br.com.cadalunos.cantina.Service;

import br.com.cadalunos.cantina.DTO.ProdutoRequest;
import br.com.cadalunos.cantina.DTO.ProdutoResponse;
import br.com.cadalunos.cantina.Entity.Categoria;
import br.com.cadalunos.cantina.Entity.Produto;
import br.com.cadalunos.cantina.repository.CategoriaRepository;
import br.com.cadalunos.cantina.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoResponse save(ProdutoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());
        produto.setAtivo(true);
        produto.setCategoria(categoria);

        log.info("Criando produto: {}", request.getNome());
        return toResponse(produtoRepository.save(produto));
    }

    public ProdutoResponse findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return toResponse(produto);
    }

    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());
        produto.setCategoria(categoria);

        log.info("Produto {} atualizado", id);
        return toResponse(produtoRepository.save(produto));
    }

    public void deleteById(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtoRepository.deleteById(id);
        log.info("Produto {} deletado", id);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getAtivo(),
                produto.getCategoria().getId(),
                produto.getCategoria().getNome()
        );
    }
}