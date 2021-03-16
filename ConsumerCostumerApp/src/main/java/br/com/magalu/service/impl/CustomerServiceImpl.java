package br.com.magalu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magalu.entity.CustomerEntity;
import br.com.magalu.entity.redis.CustomerRedisEntity;
import br.com.magalu.entity.redis.TransactionRedisEntity;
import br.com.magalu.enumerator.TransactionEnum;
import br.com.magalu.model.Customer;
import br.com.magalu.repository.CustomerRepository;
import br.com.magalu.repository.redis.CustomerRedisRepository;
import br.com.magalu.repository.redis.TransactionRedisRepository;
import br.com.magalu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CustomerRedisRepository customrRedisRepository;

	@Autowired
	private TransactionRedisRepository transactionRedisRepository;

	
	@Override
	public void save(ConsumerRecord<String, Customer> customer) {

		if (!isEmailValid(customer.value())) {
			
			transactionControl("Email alredy user in database", customer.key(), TransactionEnum.DENIED);

		} else {
			var redisEntity = new CustomerRedisEntity(customer.value());
			var redisSavedData = customrRedisRepository.save(redisEntity);

			var entity = new CustomerEntity(customer.value(), redisSavedData.getId());
			repository.save(entity);

			transactionControl("Data saved successfully", customer.key(), TransactionEnum.SUCCESS);
			
		}
	}

	@Override
	public void delete(ConsumerRecord<String, Customer> customer) {

		getDataFromRedis(customer.value()).forEach(customrRedisRepository::delete);

		repository.deleteByEmail(customer.value().getEmail());
		
		transactionControl("Data deleted successfully", customer.key(), TransactionEnum.SUCCESS);
	}

	@Override
	public void update(ConsumerRecord<String, Customer> customer) {

		var listRedis = getDataFromRedis(customer.value()).stream().map(row -> {
			row.setNome(customer.value().getNome());
			return row;
		}).collect(Collectors.toList());

		listRedis.forEach(customrRedisRepository::delete);
		customrRedisRepository.saveAll(listRedis);

		repository.update(customer.value().getEmail(), customer.value().getNome());

		transactionControl("Data updated successfully", customer.key(), TransactionEnum.SUCCESS);
	}

	private List<CustomerRedisEntity> getDataFromRedis(Customer data) {

		var redisList = new ArrayList<CustomerRedisEntity>();

		customrRedisRepository.findAll().forEach(x -> {
			if (x.getEmail().equals(data.getEmail())) {
				redisList.add(x);
			}
		});

		return redisList;
	}

	private Boolean isEmailValid(Customer data) {

		var loop = customrRedisRepository.findAll();
		var isValid = true;

		for (CustomerRedisEntity row : loop) {
			if (null != row && row.getEmail().equals(data.getEmail()))
				isValid = false;
		}

		return isValid;
	}
	
	private void transactionControl(String message, String key, TransactionEnum status) {
		var transaction = new TransactionRedisEntity(key, status.toString(), message);
		transactionRedisRepository.save(transaction);
	}

}
