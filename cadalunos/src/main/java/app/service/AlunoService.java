package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import app.controller.aluno.dto.AlunoResponse;
import app.entity.Aluno;
import app.service.AlunoService;
import lombok.RequiredArgsConstructor;
import app.controller.aluno.dto.AlunoResponse;
import app.controller.aluno.dto.AlunoRequest;
import app.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import app.entity.Aluno;


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
		
	public Aluno update() {
		
		return null;
	}
	
	public Aluno findById(Long id) {		
			
		return this.alunoRepository.findById(id)
				.orElseThrow(
						() -> new ResponseStatusException(HttpeStatus.NOT_FOUND
								"Aluno não encontrado pelo ID "+id));
	}
	
	public ArrayList<Aluno> listar(){
		
			return null;	
	}

	public Aluno deletById() {
		return null;
	}
	
}
