package com.grupo14T01.app.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private int unidades;
	private double preco;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Item> item;
	
	public Produto(int id, String nome, int unidades, double preco) {
		this.id = id;
		this.nome = nome;
		this.unidades = unidades;
		this.preco = preco;
	}
	
	public Produto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Item> getItem() {
		return item;
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
		Produto other = (Produto) obj;
		return id == other.id;
	}
}
