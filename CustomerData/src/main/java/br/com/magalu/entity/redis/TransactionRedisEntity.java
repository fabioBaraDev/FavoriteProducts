package br.com.magalu.entity.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@RedisHash("Customer")
public class TransactionRedisEntity  implements Serializable{

	private static final long serialVersionUID = -6332043978101624694L;

	public TransactionRedisEntity() {}
	
	public TransactionRedisEntity(String correlationId, String transaction, String message) {
		this.correlationId = correlationId;
		this.transaction = transaction;
		this.message = message;
	}
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String correlationId;
	
	@Getter @Setter
	private String transaction;
	
	@Getter @Setter
	private String message;
	
}
