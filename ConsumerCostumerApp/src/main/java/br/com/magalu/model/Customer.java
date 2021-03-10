package br.com.magalu.model;

import lombok.Getter;
import lombok.Setter;

public class Customer {

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String email;

	public Customer() {
	}

	public Customer(String nome, String email) {

		this.nome = nome;
		this.email = email;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Customer{");
		sb.append("name='").append(nome).append('\'');
		sb.append(", dept='").append(email).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
