package com.grupo14turma01.app.servicos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo14turma01.app.entidades.Produto;
import com.grupo14turma01.app.entidades.Venda;
import com.grupo14turma01.app.repositorios.ClienteRepositorio;
import com.grupo14turma01.app.repositorios.ProdutoRepositorio;
import com.grupo14turma01.app.repositorios.VendaRepositorio;

import jakarta.persistence.EntityManager;

@Service
public class VendaServico {
	@Autowired
	private VendaRepositorio repositorio;
	
	@Autowired
	private ClienteRepositorio repositorioCliente;
	
	@Autowired
	private ProdutoRepositorio repositorioProduto;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Venda> listar() {
        return repositorio.findAll();
    }
	
	public Venda buscarPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }
	
	public List<Venda> consultarPorNome(String nome) {
		return repositorio.consultarPorNome(nome);
	}
	
	@Transactional
    public Venda inserir(Venda venda) {
		// Checando se cliente existe
    	repositorioCliente.findById(venda.getCliente().getId()).orElseThrow(() -> new RuntimeException("Cliente não existe"));
    	// lista e soma são variáveis auxiliares
    	List<Produto> lista = new ArrayList<>();
    	int soma = 0;
    	/* Este for altera o número de unidades na tabela produtos no banco de dados, prepara a lista
    	   para preencher os campos do(s) produto(s) no JSON de resposta e usa a soma para obter o valor total */
    	for (Produto p : venda.getProdutos()) {
    		p = repositorioProduto.findById(p.getId()).get();
			p.setUnidades(p.getUnidades() - 1);
			lista.add(p);
    		soma += p.getPreco();
    		repositorioProduto.save(p);
    	}
    	venda.setNumProdutosVenda(lista.size());
    	// Preenche a lista da venda para mandar no JSON
    	venda.setProdutos(lista);
    	
    	venda.setValorTotal(soma);
    	venda.setValorPendente(soma);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
    	venda.setData(sdf.format(new Date()));
    	
        return repositorio.save(entityManager.merge(venda));
    }
	
	@Transactional
    public Venda editar(long id, Venda venda) {
		if (venda.getValorPendente() < venda.getValorTotal())
			throw new RuntimeException("Erro, não é permitido editar vendas pagas completa ou parcialmente.");
		else if (venda.getProdutos() != null)
			throw new RuntimeException("Erro, não é permitido alterar produtos de uma venda já registrada.");
    	
		Venda vendaOriginal = repositorio.findById(id).get();
		
		if (vendaOriginal.getCliente() != null && venda.getCliente() != null)
			if (vendaOriginal.getCliente().getId() != venda.getCliente().getId())
				vendaOriginal.setCliente(repositorioCliente.findById(venda.getCliente().getId()).get());
		if (venda.getCondicoes() != null)
			vendaOriginal.setCondicoes(venda.getCondicoes());
		if (venda.getFormaPagamento() != null)
			vendaOriginal.setFormaPagamento(venda.getFormaPagamento());
		if (venda.getData() != null)
			vendaOriginal.setData(venda.getData());

        return repositorio.save(entityManager.merge(vendaOriginal));
    }
	
    public void deletar(Long id, Venda venda) {
    	for (Produto p : venda.getProdutos()) {
    		p = repositorioProduto.findById(p.getId()).get();
    		p.setUnidades(p.getUnidades() + 1);
    		repositorioProduto.save(p);
		}
        repositorio.deleteById(id);
    }
}
