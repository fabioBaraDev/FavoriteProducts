package br.com.magalu.service;

import br.com.magalu.model.Customer;

public interface CustomerService {

	void save(Customer data);

	void delete(Customer data);

	void update(Customer data);
}
