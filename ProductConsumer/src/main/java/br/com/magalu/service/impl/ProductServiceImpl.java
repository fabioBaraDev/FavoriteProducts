package br.com.magalu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magalu.entity.ProductEntity;
import br.com.magalu.entity.redis.ProductRedisEntity;
import br.com.magalu.entity.redis.TransactionRedisEntity;
import br.com.magalu.enumerator.TransactionEnum;
import br.com.magalu.model.ProductModel;
import br.com.magalu.model.ProductUpdateModel;
import br.com.magalu.repository.ProductRepository;
import br.com.magalu.repository.redis.ProductRedisRepository;
import br.com.magalu.repository.redis.TransactionRedisRepository;
import br.com.magalu.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired 
	private ProductRedisRepository productRedisRepository;
	
	@Autowired
	private TransactionRedisRepository transactionRedisRepository;
	
	private void transactionControl(String message, String key, TransactionEnum status) {
		var transaction = new TransactionRedisEntity(key, status.toString(), message);
		transactionRedisRepository.save(transaction);
	
	}

	@Override
	public void save(ConsumerRecord<String, ProductModel> product) {
		var productEntity = new ProductEntity(product.value());
		var productSaved = productRepository.save(productEntity);
	
		var redisEntity = new ProductRedisEntity(productSaved);
		productRedisRepository.save(redisEntity);
		
		transactionControl("Product saved successfully", product.key(), TransactionEnum.SUCCESS);
		
	}

	@Override
	public void delete(ConsumerRecord<String, String> product) {
		var id = Integer.parseInt(product.value().toString());
		productRepository.deleteById(id);
		
		transactionControl("Product deleted successfully", product.key(), TransactionEnum.SUCCESS);
	}

	@Override
	public void update(ConsumerRecord<String, ProductUpdateModel> product) {
		
		var listRedis = getDataFromRedis(product.value()).stream().map(row -> {
			row.setTitle(product.value().getTitle());
			return row;
		}).collect(Collectors.toList());

		listRedis.forEach(productRedisRepository::delete);
		productRedisRepository.saveAll(listRedis);
		
		productRepository.updateNameById(product.value().getId(), product.value().getTitle());
		transactionControl("Product updated successfully", product.key(), TransactionEnum.SUCCESS);
		
	}
	
	private List<ProductRedisEntity> getDataFromRedis(ProductUpdateModel data) {

		var redisList = new ArrayList<ProductRedisEntity>();

		productRedisRepository.findAll().forEach(x -> {
			if (data.getId().equals(x.getId())) {
				redisList.add(x);
			}
		});

		return redisList;
	}

}
