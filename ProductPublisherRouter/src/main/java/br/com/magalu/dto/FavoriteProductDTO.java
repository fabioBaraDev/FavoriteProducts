package br.com.magalu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class FavoriteProductDTO {

	@Getter @Setter @JsonProperty("product-id")
	private Integer productId;
	
	@Getter @Setter @JsonProperty("client-id")
	private Integer clientId;
}
