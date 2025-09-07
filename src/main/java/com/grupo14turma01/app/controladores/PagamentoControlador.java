package com.grupo14turma01.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo14turma01.app.entidades.Pagamento;
import com.grupo14turma01.app.servicos.PagamentoServico;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoControlador {
	
	@Autowired
	private PagamentoServico servico;
	
	@GetMapping
    public List<Pagamento> listar() {
        return servico.listar();
    }
	
	@GetMapping("/{id}")
    public Pagamento buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }
	
	@PostMapping("/{id}")
    public Pagamento inserir(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        return servico.inserir(id, pagamento);
    }
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servico.deletar(id);
    }
}
