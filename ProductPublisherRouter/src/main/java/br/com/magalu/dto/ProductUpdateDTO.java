package br.com.magalu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class ProductUpdateDTO {

	
	@Getter @Setter @JsonProperty("id")
	private Integer id;
	
	
	@Getter @Setter @JsonProperty("title")
	private String title;
}
