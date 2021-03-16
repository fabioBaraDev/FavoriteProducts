package br.com.magalu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import br.com.magalu.model.FavoriteProductModel;
import br.com.magalu.model.ProductModel;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfiguration {

	@Value("${redis.clients.server}")
	private String server;
	
	@Value("${redis.clients.server.port}")
	private String port;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName(server);
	    jedisConFactory.setPort(Integer.parseInt(port));
	    return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, ProductModel> redisTemplate() {
	    RedisTemplate<String, ProductModel> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
	
	@Bean
	public RedisTemplate<String, FavoriteProductModel> redisTemplate2() {
	    RedisTemplate<String, FavoriteProductModel> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
	
	
}
