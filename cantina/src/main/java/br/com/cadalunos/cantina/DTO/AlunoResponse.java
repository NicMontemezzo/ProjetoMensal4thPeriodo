package br.com.cadalunos.cantina.DTO;

import br.com.cadalunos.cantina.Entity.Aluno;

public record AlunoResponse(
        Long id,
        String nome,
        String sexo,
        int anoNascimento
) {
    public static AlunoResponse de(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getSexo(),
                aluno.getAnoNascimento()
        );
    }
}