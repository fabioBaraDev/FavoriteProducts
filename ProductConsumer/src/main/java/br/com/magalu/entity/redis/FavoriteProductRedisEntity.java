package br.com.magalu.entity.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("FavoriteProduct")
public class FavoriteProductRedisEntity  implements Serializable{

	private static final long serialVersionUID = 3695233960938133882L;

}
