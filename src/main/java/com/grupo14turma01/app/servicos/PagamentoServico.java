package com.grupo14turma01.app.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo14turma01.app.entidades.Pagamento;
import com.grupo14turma01.app.entidades.Venda;
import com.grupo14turma01.app.repositorios.PagamentoRepositorio;
import com.grupo14turma01.app.repositorios.VendaRepositorio;

@Service
public class PagamentoServico {
	
	@Autowired
	private PagamentoRepositorio repositorio;
	
	@Autowired
	private VendaRepositorio repositorioVenda;

	public List<Pagamento> listar() {
        return repositorio.findAll();
    }
	
	public Pagamento buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }
	
	//inserir serve para editar também
    public Pagamento inserir(long id, Pagamento pagamento) {
    	pagamento.setVenda(repositorioVenda.findById(id).get());
    	Venda v = pagamento.getVenda();
    	v.setValorPendente(v.getValorPendente() - pagamento.getValor());
    	if (v.getValorPendente() == 0)
    		v.setStatusPagamento(true);
    	
    	repositorioVenda.save(v);
        return repositorio.save(pagamento);
    }

    public void deletar(Long id) {
    	Pagamento p = repositorio.findById(id).get();
    	Venda v = p.getVenda();
    	v.setValorPendente(v.getValorPendente() + p.getValor());
    	if (v.isStatusPagamento() == true)
    		v.setStatusPagamento(false);
    	repositorioVenda.save(v);
        repositorio.deleteById(id);
    }
}
