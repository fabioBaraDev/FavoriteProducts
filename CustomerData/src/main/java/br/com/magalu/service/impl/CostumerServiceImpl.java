package br.com.magalu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magalu.model.Costumer;
import br.com.magalu.model.Transaction;
import br.com.magalu.repository.CustomerRepository;
import br.com.magalu.repository.redis.CostumerRedisRepository;
import br.com.magalu.repository.redis.TransactionRedisRepository;
import br.com.magalu.service.CostumerService;

@Service
public class CostumerServiceImpl implements CostumerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CostumerRedisRepository customerRedisRepository;

	@Autowired
	private TransactionRedisRepository transactionRedisRepository;

	@Override
	public Costumer getCostumer(String email) {

		var costumers = new ArrayList<Costumer>();

		customerRedisRepository.findAll().forEach(row -> {

			if (null != row && row.getEmail().equals(email)) {
				costumers.add(new Costumer(row.getNome(), row.getEmail()));
			}
		});

		if (costumers.size() == 0) {
			var costumer = new Costumer(repository.findByEmail(email).get());
			costumers.add(costumer);
		}

		return costumers.get(0);
	}

	@Override
	public List<Costumer> getAllCostumers() {

		var costumers = new ArrayList<Costumer>();

		customerRedisRepository.findAll().forEach(row -> {
			if(null != row)
				costumers.add(new Costumer(row.getNome(), row.getEmail()));
		});

		return costumers;
	}

	@Override
	public Transaction getTransactionStatus(String correlationId) {

		var transaction = new ArrayList<Transaction>();
		
		transactionRedisRepository.findAll().forEach(row -> {
			if(null != row)
				transaction.add(new Transaction(row));
		});
		
		
		return transaction.get(0);
	}

}
