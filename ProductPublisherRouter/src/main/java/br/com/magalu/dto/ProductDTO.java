package br.com.magalu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class ProductDTO {
	
	@Getter @Setter @JsonProperty("price")
	private Double price;
	
	@Getter @Setter @JsonProperty("image")
	private String image;
	
	@Getter @Setter @JsonProperty("brand")
	private String brand;
	
	@Getter @Setter @JsonProperty("title")
	private String title;
	
	@Getter @Setter @JsonProperty("reviewScore")
	private Double reviewScore;
	
}
