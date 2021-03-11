package br.com.magalu.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import br.com.magalu.model.Customer;

public interface CustomerService {

	void save(ConsumerRecord<String, Customer> customer);

	void delete(ConsumerRecord<String, Customer> customer);

	void update(ConsumerRecord<String, Customer> customer);
}
