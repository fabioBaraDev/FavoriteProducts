package br.com.magalu.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import br.com.magalu.model.Customer;
import br.com.magalu.service.CustomerService;

@Service
public class KafkaConsumer {

	@Autowired
	private CustomerService service;


	@KafkaListener(topics = "${topic.name.consumer.save}", groupId = "${topic.name.group}", containerFactory = "userKafkaListenerFactory")
	public void consumeSave(ConsumerRecord<String, Customer> customer) {
		
		service.save(customer);
		
		System.out.println("Consumed JSON Message to save data: " + customer);
	}
	

	@KafkaListener(topics = "${topic.name.consumer.delete}", groupId = "${topic.name.group}", containerFactory = "userKafkaListenerFactory")
	public void consumeDelete(ConsumerRecord<String, Customer> customer) {
		
		service.delete(customer);
		
		System.out.println("Consumed JSON Message to delete data: " + customer);
	}
	

	@KafkaListener(topics = "${topic.name.consumer.update}", groupId = "${topic.name.group}", containerFactory = "userKafkaListenerFactory")
	public void consumeUpdate(ConsumerRecord<String, Customer> customer) {
		
		service.update(customer);
		
		System.out.println("Consumed JSON Message to update data: " + customer);
	}
}
