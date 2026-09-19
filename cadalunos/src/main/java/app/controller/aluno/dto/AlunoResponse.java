package app.controller.aluno.dto;

import app.entity.Aluno;
import app.service.AlunoService;


public record AlunoResponse(
		long id,
	    String nome,
		String sexo,
		int anoNascimento
		)
{
	public static AlunoResponse de(Aluno aluno) {
		return new AlunoResponse(
				aluno.getId(),
				aluno.getAnoNascimento(),
				aluno.getNome(),
				aluno.getSexo()
				);
	}

}
