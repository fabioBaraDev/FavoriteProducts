package br.com.magalu.service;

import org.springframework.http.ResponseEntity;

import br.com.magalu.dto.FavoriteProductDTO;
import br.com.magalu.dto.ProductDTO;
import br.com.magalu.dto.ProductUpdateDTO;

public interface ProductService {

	public ResponseEntity<String> saveProduct(String correlationID, ProductDTO product);
	public ResponseEntity<String> saveFavoriteProduct(String correlationID, FavoriteProductDTO favoriteProduct);
	public ResponseEntity<String> deleteProduct(String correlationID, Integer id);
	public ResponseEntity<String> updateProductName(String correlationID, ProductUpdateDTO prduct);
	public ResponseEntity<String> deleteFavoriteProduct(String correlationID, Integer id, Integer costumerId);
	
}
