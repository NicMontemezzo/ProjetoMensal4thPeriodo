package br.com.cadalunos.cantina.repository;

import br.com.cadalunos.cantina.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}