package com.grupo14turma01.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo14turma01.app.controladores.util.URL;
import com.grupo14turma01.app.entidades.Produto;
import com.grupo14turma01.app.servicos.ProdutoServico;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoControlador {
	
	@Autowired
	private ProdutoServico servico;
	
	@GetMapping
    public List<Produto> listar() {
        return servico.listar();
    }
	
	@GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }
	
	@GetMapping("/consultarnome")
	public List<Produto> consultarPorNome(@RequestParam String nome) {
		URL.decodificarParamURL(nome);
		return servico.consultarPorNome("%"+nome+"%");
	}
	
	@PostMapping
    public Produto inserir(@RequestBody Produto produto) {
        return servico.inserir(produto);
    }
	
	@PutMapping("/{id}")
	public Produto editar(@PathVariable Long id, @RequestBody Produto produto) {
		produto.setId(id);
        return servico.inserir(produto);
    }
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servico.deletar(id);
    }
}
