package com.amazonaws.lambda.demo;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	
	String nome;
	String idade;
	List<String> lista = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public List<String> getLista() {
		return lista;
	}
	
	public void setLista(List<String> lista) {
		this.lista = lista;
	}
	
	
}
