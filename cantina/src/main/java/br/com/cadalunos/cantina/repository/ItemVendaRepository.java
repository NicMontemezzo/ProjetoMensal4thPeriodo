package br.com.cadalunos.cantina.repository;

import br.com.cadalunos.cantina.Entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}