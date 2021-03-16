package br.com.magalu.repository.redis;

import org.springframework.data.repository.CrudRepository;

import br.com.magalu.entity.redis.ProductRedisEntity;

public interface ProductRedisRepository extends CrudRepository<ProductRedisEntity, String> {}
