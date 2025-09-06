package com.grupo14turma01.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo14turma01.app.entidades.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long>{
	
	@Query("select v from Venda v where LOWER(v.cliente.nome) like LOWER(?1)")
	List<Venda> consultarPorNome(String nome);
}
