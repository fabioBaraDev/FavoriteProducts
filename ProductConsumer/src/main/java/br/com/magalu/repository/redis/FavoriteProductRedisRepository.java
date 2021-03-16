package br.com.magalu.repository.redis;

import org.springframework.data.repository.CrudRepository;

import br.com.magalu.entity.redis.FavoriteProductRedisEntity;

public interface FavoriteProductRedisRepository extends CrudRepository<FavoriteProductRedisEntity, String> {}