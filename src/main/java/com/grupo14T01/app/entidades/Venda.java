package com.grupo14T01.app.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<Item> itens;
	
	private double valor;
	private String condicoes;
	private String formaPagamento;
	private LocalDateTime data = LocalDateTime.now();
	
	public Venda(int id, Cliente cliente, List<Item> itens, double valor, String condicoes, String formaPagamento,
			LocalDateTime data) {
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
		this.valor = valor;
		this.condicoes = condicoes;
		this.formaPagamento = formaPagamento;
		this.data = data;
	}

	public Venda() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
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
