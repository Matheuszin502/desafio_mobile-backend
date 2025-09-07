package com.grupo14turma01.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo14turma01.app.entidades.Pagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long>{
}
