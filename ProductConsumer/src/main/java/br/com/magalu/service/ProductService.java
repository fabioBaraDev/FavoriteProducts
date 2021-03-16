package br.com.magalu.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import br.com.magalu.model.ProductModel;
import br.com.magalu.model.ProductUpdateModel;

public interface ProductService {

	public void save(ConsumerRecord<String, ProductModel> product);
	public void delete(ConsumerRecord<String, String> product);
	public void update(ConsumerRecord<String, ProductUpdateModel> product);
}
