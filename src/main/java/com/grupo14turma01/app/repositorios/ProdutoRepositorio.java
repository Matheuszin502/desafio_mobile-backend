package com.grupo14turma01.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo14turma01.app.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{
	
	@Query("select p from Produto p where LOWER(p.nome) like LOWER(?1)")
	List<Produto> consultarPorNome(String nome);
}
