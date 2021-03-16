package br.com.magalu.model;

import lombok.Getter;
import lombok.Setter;

public class ProductModel {

	
	@Getter @Setter 
	private Double price;
	
	@Getter @Setter 
	private String image;
	
	@Getter @Setter 
	private String brand;
	
	@Getter @Setter 
	private String title;
	
	@Getter @Setter 
	private Double reviewScore;
}
