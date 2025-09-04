package com.grupo14turma01.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo14turma01.app.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
}
