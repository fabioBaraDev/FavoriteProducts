package br.com.magalu.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.magalu.model.FavoriteProductModel;
import br.com.magalu.service.FavoriteProductService;

@Service
public class KafkaConsumerFavoriteProduct {

	@Autowired
	private FavoriteProductService service;

	@KafkaListener(topics = "${topic.name.favorite.product.save}", groupId = "${topic.name.group}", containerFactory = "favoriteKafkaListenerFactory")
	public void favoriteProductSave(ConsumerRecord<String, FavoriteProductModel> favoriteProduct) {
		
		this.service.save(favoriteProduct.value());
		
		System.out.println("Consumed JSON Message to save data: " + favoriteProduct.value());
	}
	

	@KafkaListener(topics = "${topic.name.favorite.product.delete}", groupId = "${topic.name.group}", containerFactory = "favoriteKafkaListenerFactory")
	public void favoriteProductDelete(ConsumerRecord<String, FavoriteProductModel> favoriteProductModel) {
		
		this.service.delete(favoriteProductModel.value());
		
		System.out.println("Consumed JSON Message to delete data: " + favoriteProductModel.value());
	}
	


}
