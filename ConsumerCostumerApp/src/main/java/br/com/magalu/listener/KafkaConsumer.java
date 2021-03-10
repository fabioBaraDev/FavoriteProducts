package br.com.magalu.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.magalu.model.Customer;
import br.com.magalu.service.CustomerService;

@Service
public class KafkaConsumer {

	@Value("${topic.name.consumer.save")
	private String saveTopicName;
	
	@Autowired
	private CustomerService service;

	@KafkaListener(topics = "${topic.name.consumer.save}", group = "group_json", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Customer customer) {
		
		service.save(customer);
		
		System.out.println("Consumed JSON Message: " + customer);
	}
}
