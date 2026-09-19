package br.com.cadalunos.cantina.repository;

import br.com.cadalunos.cantina.Entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}