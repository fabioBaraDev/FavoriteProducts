package br.com.magalu.service.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import br.com.magalu.dto.FavoriteProductDTO;
import br.com.magalu.dto.ProductDTO;
import br.com.magalu.dto.ProductUpdateDTO;
import br.com.magalu.kafka.KafkaDispatcherService;
import br.com.magalu.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private KafkaDispatcherService kafkaService;

	@Value("${kafka.queue.name.save.product}") //"SAVE_PRODUCT"
	private String queueSaveProduct;
	
	@Value("${kafka.queue.name.save.favorite.product}") //"SAVE_FAVORITE_PRODUCT
	private String queueSaveFavoriteProduct;
	
	@Value("${kafka.queue.name.delete.product}") //"DELETE_PRODUCT"
	private String queueDeleteProduct;
	
	@Value("${kafka.queue.name.delete.favorite.product}") //""DELETE_FAVORITE_PRODUCT""
	private String queueDeleteFavoriteProduct;
	
	@Value("${kafka.queue.name.update.product}") //"UPDATE_PRODUCT_NAME"
	private String queueUpdateProductName;
	
	
	@Override
	public ResponseEntity<String> saveProduct(String correlationID, ProductDTO product) {
		
		return sendMes(product, correlationID, queueSaveProduct);
	}

	@Override
	public ResponseEntity<String> saveFavoriteProduct(String correlationID, FavoriteProductDTO favoriteProduct) {
		return sendMes(favoriteProduct, correlationID, queueSaveFavoriteProduct);
		
	}

	@Override
	public ResponseEntity<String> deleteProduct(String correlationID, Integer id) {
		
		return sendMes(id, correlationID, queueDeleteProduct);
	}

	@Override
	public ResponseEntity<String> deleteFavoriteProduct(String correlationID, Integer id, Integer costumerId) {
		var productDTO = new FavoriteProductDTO();
		productDTO.setClientId(costumerId);
		productDTO.setProductId(id);
		return sendMes(productDTO, correlationID, queueDeleteFavoriteProduct);
		
	}

	@Override
	public ResponseEntity<String> updateProductName(String correlationID, ProductUpdateDTO prduct) {
		return sendMes(prduct, correlationID, queueUpdateProductName);
		
	}
	
	private ResponseEntity<String> sendMes(Object data, String correlationID, String queue) {
		try {

			kafkaService.send(queue, correlationID, new GsonBuilder().create().toJson(data));
		} catch (InterruptedException | ExecutionException e) {

			return new ResponseEntity<String>("Error tring to save in the queue: " + queue, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Data is being process", HttpStatus.OK);
	}

}
