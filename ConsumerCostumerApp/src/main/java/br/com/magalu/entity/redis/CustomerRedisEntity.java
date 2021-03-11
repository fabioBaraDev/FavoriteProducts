package br.com.magalu.entity.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import br.com.magalu.model.Customer;
import lombok.Getter;
import lombok.Setter;

@RedisHash("Customer")
public class CustomerRedisEntity implements Serializable {

	private static final long serialVersionUID = 8323892974236476911L;

	public CustomerRedisEntity() {}	
	
	public CustomerRedisEntity(Customer customer) {
		this.email = customer.getEmail();
		this.nome = customer.getNome();
	}
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	@Indexed private String email;


}

