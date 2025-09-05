package com.grupo14turma01.app.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo14turma01.app.entidades.Cliente;
import com.grupo14turma01.app.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio repositorio;

	public List<Cliente> listar() {
        return repositorio.findAll();
    }
	
	public Cliente buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
	
	public List<Cliente> consultarPorNome(String nome) {
		return repositorio.consultarPorNome(nome);
	}
	
	//inserir serve para editar também
    public Cliente inserir(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}
