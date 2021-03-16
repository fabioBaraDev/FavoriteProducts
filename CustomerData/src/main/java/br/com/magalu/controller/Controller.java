package br.com.magalu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magalu.model.Costumer;
import br.com.magalu.model.Transaction;
import br.com.magalu.service.CostumerService;

@RestController
@RequestMapping("costumer")
public class Controller {

	@Autowired
	private CostumerService service;
	
	@GetMapping("/{email}")
	public ResponseEntity<Costumer> getCostumer(@PathVariable String email) {
		
		return new ResponseEntity<>(service.getCostumer(email), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Costumer>> getAllCostumers() {
		
		return new ResponseEntity<>(service.getAllCostumers(), HttpStatus.OK);
	}

	@GetMapping("/transaction/{correlationId}")
	public ResponseEntity<Transaction> getTransactionStatus(@PathVariable String correlationId) {
		
		return new ResponseEntity<>(service.getTransactionStatus(correlationId), HttpStatus.OK);
	}
}
