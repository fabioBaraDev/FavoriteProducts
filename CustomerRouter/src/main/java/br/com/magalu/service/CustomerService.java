package br.com.magalu.service;

import org.springframework.http.ResponseEntity;

import br.com.magalu.dto.RequestDTO;

public interface CustomerService {

	ResponseEntity<String> save(RequestDTO data, String correlationID);
	ResponseEntity<String> update(RequestDTO data, String correlationID);
	ResponseEntity<String> delete(String email, String correlationID);
}
