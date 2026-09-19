package br.com.cadalunos.cantina.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;
import br.com.cadalunos.cantina.DTO.AlunoRequest;
import br.com.cadalunos.cantina.Entity.Aluno;
import br.com.cadalunos.cantina.repository.AlunoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno save(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.getNome());
        aluno.setAnoNascimento(alunoRequest.getAnoNascimento());
        aluno.setSexo(alunoRequest.getSexo());

        return this.alunoRepository.save(aluno);
    }

    public Aluno update(Long id, AlunoRequest alunoRequest) {
        Aluno aluno = this.findById(id);
        aluno.setNome(alunoRequest.getNome());
        aluno.setSexo(alunoRequest.getSexo());
        aluno.setAnoNascimento(alunoRequest.getAnoNascimento());

        return this.alunoRepository.save(aluno);
    }

    public Aluno findById(Long id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Aluno não encontrado pelo ID " + id));
    }

    public List<Aluno> listar() {
        return this.alunoRepository.findAll();
    }

    public void deleteById(Long id) {
        Aluno aluno = this.findById(id);
        this.alunoRepository.delete(aluno);
    }
}