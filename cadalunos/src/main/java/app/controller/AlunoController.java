package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import app.controller.aluno.dto.AlunoResponse;
import app.entity.Aluno;
import app.service.AlunoService;
import lombok.RequiredArgsConstructor;
import app.controller.aluno.dto.AlunoResponse;
import app.controller.aluno.dto.AlunoRequest;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {
	
	@Autowired
	private final AlunoService alunoService;
	
	@PostMapping()
	public ResponseEntity<AlunoResponse> save(@RequestBody AlunoRequest alunoRequest){
		
		try {
			Aluno aluno = this.alunoService.save(alunoRequest);
			return new ResponseEntity<AlunoResponse>(AlunoResponse.de(aluno), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity( HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponse> findById(@PathVariable Long id){
		
		try {
			Aluno aluno = this.alunoService.findById(id);
			return new ResponseEntity<AlunoResponse>(AlunoResponse.de(aluno), HttpStatus.CREATED);
		} 
			catch (ResponseStatusException responseStatusException) {
			throw responseStatusException;
    }
		
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
