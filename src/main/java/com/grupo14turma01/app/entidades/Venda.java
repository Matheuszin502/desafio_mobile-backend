package com.grupo14turma01.app.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	private int numProdutosVenda;
	private String condicoes;
	private String formaPagamento;
	private double valorTotal;
	private boolean statusPagamento;
	
	private String data;
	
	public Venda(long id, Cliente cliente, List<Produto> produtos, int numProdutosVenda, String condicoes, String formaPagamento, 
			double valorTotal, boolean statusPagamento, String data) {
		this.id = id;
		this.cliente = cliente;
		this.produtos = produtos;
		this.numProdutosVenda = numProdutosVenda;
		this.valorTotal = valorTotal;
		this.condicoes = condicoes;
		this.formaPagamento = formaPagamento;
		this.statusPagamento = statusPagamento;
		this.data = data;
	}

	public Venda() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getNumProdutosVenda() {
		return numProdutosVenda;
	}

	public void setNumProdutosVenda(int numProdutosVenda) {
		this.numProdutosVenda = numProdutosVenda;
	}

	public String getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(String condicoes) {
		this.condicoes = condicoes;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(boolean statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return id == other.id;
	}
}
