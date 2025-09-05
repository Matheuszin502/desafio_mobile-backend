package com.grupo14turma01.app.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo14turma01.app.entidades.Produto;
import com.grupo14turma01.app.repositorios.ProdutoRepositorio;

@Service
public class ProdutoServico {
	
	@Autowired
	private ProdutoRepositorio repositorio;

	public List<Produto> listar() {
        return repositorio.findAll();
    }
	
	public Produto buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
	
	public List<Produto> consultarPorNome(String nome) {
		return repositorio.consultarPorNome(nome);
	}
	
	//inserir serve para editar também
    public Produto inserir(Produto produto) {
        return repositorio.save(produto);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}
