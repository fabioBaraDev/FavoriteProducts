package br.com.magalu.model;

import br.com.magalu.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

public class Costumer {

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String email;
	
	public Costumer() {
	}
	
	public Costumer(CustomerEntity entity) {
		this.nome = entity.getNome();
		this.email = entity.getEmail();
	}

	public Costumer(String nome, String email) {

		this.nome = nome;
		this.email = email;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Customer{");
		sb.append("name='").append(nome).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
