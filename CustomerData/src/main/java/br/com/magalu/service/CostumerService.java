package br.com.magalu.service;

import java.util.List;

import br.com.magalu.model.Costumer;
import br.com.magalu.model.Transaction;

public interface CostumerService {

	public Costumer getCostumer(String email);
	public List<Costumer> getAllCostumers();
	public Transaction getTransactionStatus(String correlationId);
}
