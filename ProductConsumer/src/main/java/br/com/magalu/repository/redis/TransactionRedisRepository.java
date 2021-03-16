package br.com.magalu.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.magalu.entity.redis.TransactionRedisEntity;

@Repository 
public interface TransactionRedisRepository extends CrudRepository<TransactionRedisEntity, String> {}


