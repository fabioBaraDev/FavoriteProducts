package br.com.magalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magalu.dto.RequestDTO;
import br.com.magalu.service.CustomerService;

@RestController
@RequestMapping("costumer")
public class Controller {

	@Autowired
	private CustomerService service;

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteData(
			@RequestHeader("correlation-id") String correlationId,
			@PathVariable String email) {

		return service.delete(email, correlationId);
	}

	@PostMapping()
	public ResponseEntity<String> postData(
			@RequestHeader("correlation-id") String correlationId,
			@RequestBody @Validated RequestDTO request) {
		
		return service.save(request, correlationId);
	}

	@PutMapping()
	public ResponseEntity<String> putData(
			@RequestHeader("correlation-id") String correlationId,
			@RequestBody RequestDTO request) {

		return service.update(request, correlationId);
	}

}
