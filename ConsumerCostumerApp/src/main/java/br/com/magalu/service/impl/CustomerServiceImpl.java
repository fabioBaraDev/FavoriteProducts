package br.com.magalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magalu.entity.CustomerEntity;
import br.com.magalu.model.Customer;
import br.com.magalu.repository.CustomerRepository;
import br.com.magalu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public void save(Customer data) {

		System.out.println("AQUI");
		
		var entity = new CustomerEntity();
		entity.setEmail(data.getEmail());
		entity.setNome(data.getNome());
		repository.save(entity);

	}

	@Override
	public void delete(Customer data) {

		repository.deleteByEmail(data.getEmail());
	}

	@Override
	public void update(Customer data) {

		repository.update(data.getEmail(), data.getNome());
	}

}
