package com.grupo14turma01.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo14turma01.app.entidades.Cliente;
import com.grupo14turma01.app.servicos.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
	
	@Autowired
	private ClienteServico servico;
	
	@GetMapping
    public List<Cliente> listar() {
        return servico.listar();
    }
	
	@GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }
	
	@PostMapping
    public Cliente inserir(@RequestBody Cliente cliente) {
        return servico.inserir(cliente);
    }
	
	@PutMapping("/{id}")
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		cliente.setId(id);
        return servico.inserir(cliente);
    }
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servico.deletar(id);
    }
}
