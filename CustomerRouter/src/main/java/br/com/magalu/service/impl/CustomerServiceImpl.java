package br.com.magalu.service.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import br.com.magalu.dto.RequestDTO;
import br.com.magalu.kafka.impl.KafkaDispatcherService;
import br.com.magalu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private KafkaDispatcherService kafkaService;

	@Override
	public ResponseEntity<String> save(RequestDTO data, String correlationID) {

		return sendMes(data, correlationID, "SAVE_CUSTOMER");
	}

	@Override
	public ResponseEntity<String> update(RequestDTO data, String correlationID) {

		return sendMes(data, correlationID, "UPDATE_CUSTOMER");
	}

	@Override
	public ResponseEntity<String> delete(String email, String correlationID) {
		var data = new RequestDTO();
		data.setEmail(email);
		return sendMes(data, correlationID, "DELETE_CUSTOMER");
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
