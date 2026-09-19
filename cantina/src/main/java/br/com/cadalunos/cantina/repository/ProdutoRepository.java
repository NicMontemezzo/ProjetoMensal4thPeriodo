package br.com.cadalunos.cantina.repository;

import br.com.cadalunos.cantina.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}