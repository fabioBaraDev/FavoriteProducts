package br.com.magalu.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.magalu.model.ProductModel;
import br.com.magalu.model.ProductUpdateModel;
import br.com.magalu.service.ProductService;

@Service
public class KafkaConsumerProduct {

	@Autowired
	private ProductService productService;

	@KafkaListener(topics = "${topic.name.product.save}", groupId = "${topic.name.group}", containerFactory = "productKafkaListenerFactory")
	public void productSave(ConsumerRecord<String, ProductModel> product) {
		
		this.productService.save(product);
		
		System.out.println("Consumed JSON Message to save data: " + product.value());
	}
	

	@KafkaListener(topics = "${topic.name.product.delete}", groupId = "${topic.name.group}", containerFactory = "kafkaListenerContainerFactory")
	public void productDelete(ConsumerRecord<String, String> product) {
		
		this.productService.delete(product);
		
		System.out.println("Consumed JSON Message to delete data: " + product.value().toString());
	}
	

	@KafkaListener(topics = "${topic.name.product.update}", groupId = "${topic.name.group}", containerFactory = "updateFavoriteKafkaListenerFactory")
	public void productUpdate(ConsumerRecord<String, ProductUpdateModel> product) {
		
		this.productService.update(product);
		
		System.out.println("Consumed JSON Message to update data: " + product.value());
	}
	
}
