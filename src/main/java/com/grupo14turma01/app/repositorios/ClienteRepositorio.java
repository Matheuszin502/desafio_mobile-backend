package com.grupo14turma01.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo14turma01.app.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where LOWER(c.nome) like LOWER(?1)")
	List<Cliente> consultarPorNome(String nome);
}
