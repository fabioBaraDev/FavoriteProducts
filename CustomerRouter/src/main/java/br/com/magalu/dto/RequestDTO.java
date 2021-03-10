package br.com.magalu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class RequestDTO {
	
	@Getter @Setter @JsonProperty("nome")
	private String nome;
	
	@Getter @Setter @JsonProperty("email")
	private String email;
}
