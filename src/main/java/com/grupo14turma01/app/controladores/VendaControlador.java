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
import com.grupo14turma01.app.entidades.Venda;
import com.grupo14turma01.app.servicos.VendaServico;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaControlador {
	@Autowired
	private VendaServico servico;
	
	@GetMapping
    public List<Venda> listar() {
        return servico.listar();
    }
	
	@GetMapping("/{id}")
    public Venda buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }
	
	@GetMapping("/consultarnome")
	public List<Venda> consultarPorNome(@RequestParam String nome) {
		URL.decodificarParamURL(nome);
		return servico.consultarPorNome("%"+nome+"%");
	}
	
	@PostMapping
    public Venda inserir(@RequestBody Venda venda) {
        return servico.inserir(venda);
    }
	
	@PutMapping("/{id}")
	public Venda editar(@PathVariable Long id, @RequestBody Venda venda) {
		venda.setId(id);
        return servico.inserir(venda);
    }
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id, @RequestBody Venda venda) {
        venda.setId(id);
		servico.deletar(id, venda);
    }
}
