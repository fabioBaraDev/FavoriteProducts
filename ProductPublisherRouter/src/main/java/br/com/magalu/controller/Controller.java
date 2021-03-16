package br.com.magalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magalu.dto.FavoriteProductDTO;
import br.com.magalu.dto.ProductDTO;
import br.com.magalu.dto.ProductUpdateDTO;
import br.com.magalu.service.ProductService;

@RestController
@RequestMapping("product")
public class Controller {

	@Autowired
	private ProductService service;

	@PostMapping()
	public ResponseEntity<String> salveProduct(
			@RequestHeader("correlation-id") String correlationId,
			@RequestBody @Validated ProductDTO request) {
		
		return this.service.saveProduct(correlationId, request);
	}

	@PutMapping
	public ResponseEntity<String> updateProduct(
			@RequestHeader("correlation-id") String correlationId,
			@RequestBody @Validated ProductUpdateDTO request) {
		
		return this.service.updateProductName(correlationId, request);
	}

	@PostMapping("/favorite")
	public ResponseEntity<String> salveFavoriteProduct(
			@RequestHeader("correlation-id") String correlationId,
			@RequestBody @Validated FavoriteProductDTO request) {
		
		this.service.saveFavoriteProduct(correlationId, request);
		
		return new ResponseEntity<>("Your request it`s processing!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(
			@RequestHeader("correlation-id") String correlationId,
			@PathVariable Integer id) {
		
		return this.service.deleteProduct(correlationId, id);
	}

	@DeleteMapping("/favorite/{id}/{costumer-id}")
	public ResponseEntity<String> deleteFavoriteProduct(
			@RequestHeader("correlation-id") String correlationId,
			@PathVariable("id") Integer productId, 
			@PathVariable("costumer-id") Integer costumerId) {
		
		return this.service.deleteFavoriteProduct(correlationId, productId, costumerId);
	}

}
