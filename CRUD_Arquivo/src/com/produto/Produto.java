package com.produto;

public class Produto {
	private int codigo;
	private String nome;
	private double preco;
	
	private static int contadorProduto = 0;
	
	public Produto(){
		contadorProduto++;
		this.codigo = contadorProduto;
	}
	public Produto(String nome, double preco){
		contadorProduto++;
		this.codigo = contadorProduto;
		this.nome = nome;
		this.preco = preco;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
