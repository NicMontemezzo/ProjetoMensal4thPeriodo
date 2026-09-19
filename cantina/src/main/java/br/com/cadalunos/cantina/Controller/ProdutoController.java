package br.com.cadalunos.cantina.Controller;

import br.com.cadalunos.cantina.DTO.ProdutoRequest;
import br.com.cadalunos.cantina.DTO.ProdutoResponse;
import br.com.cadalunos.cantina.Service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse create(@Valid @RequestBody ProdutoRequest request) {
        return produtoService.save(request);
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponse findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponse update(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        return produtoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        produtoService.deleteById(id);
    }
}