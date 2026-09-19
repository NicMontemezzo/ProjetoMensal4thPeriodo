package br.com.cadalunos.cantina.repository;

import br.com.cadalunos.cantina.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}