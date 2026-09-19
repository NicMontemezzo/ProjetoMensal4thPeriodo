package br.com.cadalunos.cantina.Controller;

import br.com.cadalunos.cantina.DTO.VendaRequest;
import br.com.cadalunos.cantina.DTO.VendaResponse;
import br.com.cadalunos.cantina.Service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponse create(@Valid @RequestBody VendaRequest request) {
        return vendaService.registrarVenda(request);
    }

    @GetMapping
    public List<VendaResponse> listar() {
        return vendaService.listar();
    }

    @GetMapping("/{id}")
    public VendaResponse findById(@PathVariable Long id) {
        return vendaService.findById(id);
    }
}