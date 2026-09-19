package br.com.cadalunos.cantina.Service;

import br.com.cadalunos.cantina.DTO.CategoriaRequest;
import br.com.cadalunos.cantina.DTO.CategoriaResponse;
import br.com.cadalunos.cantina.Entity.Categoria;
import br.com.cadalunos.cantina.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaResponse save(CategoriaRequest request) {
        log.info("Criando categoria: {}", request.getNome());
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        Categoria salva = categoriaRepository.save(categoria);
        return toResponse(salva);
    }

    public CategoriaResponse findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Categoria não encontrada: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
                });
        return toResponse(categoria);
    }

    public List<CategoriaResponse> listar() {
        return categoriaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoriaResponse update(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        log.info("Categoria {} atualizada", id);
        return toResponse(categoriaRepository.save(categoria));
    }

    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
        log.info("Categoria {} deletada", id);
    }

    private CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }
}