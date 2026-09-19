package br.com.cadalunos.cantina.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import br.com.cadalunos.cantina.DTO.AlunoRequest;
import br.com.cadalunos.cantina.DTO.AlunoResponse;
import br.com.cadalunos.cantina.Entity.Aluno;
import br.com.cadalunos.cantina.Service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> save(@RequestBody AlunoRequest alunoRequest) {
        Aluno aluno = this.alunoService.save(alunoRequest);
        return new ResponseEntity<>(AlunoResponse.de(aluno), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findById(@PathVariable Long id) {
        Aluno aluno = this.alunoService.findById(id);
        return new ResponseEntity<>(AlunoResponse.de(aluno), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar() {
        List<AlunoResponse> alunos = this.alunoService.listar()
                .stream()
                .map(AlunoResponse::de)
                .toList();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest) {
        Aluno aluno = this.alunoService.update(id, alunoRequest);
        return new ResponseEntity<>(AlunoResponse.de(aluno), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.alunoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}