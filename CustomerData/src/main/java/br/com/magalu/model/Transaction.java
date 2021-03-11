package br.com.magalu.model;

import br.com.magalu.entity.redis.TransactionRedisEntity;
import lombok.Getter;
import lombok.Setter;

public class Transaction {
//TransactionRedisEntity

	public Transaction() {}
	
	public Transaction(TransactionRedisEntity entity) {
		this.transaction = entity.getTransaction();
		this.message = entity.getMessage();
	}
	
	@Getter @Setter
	private String transaction;
	
	@Getter @Setter
	private String message;
}
