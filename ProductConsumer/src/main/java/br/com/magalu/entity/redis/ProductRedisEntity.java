package br.com.magalu.entity.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import br.com.magalu.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@RedisHash("Product")
public class ProductRedisEntity implements Serializable {

	private static final long serialVersionUID = -2216323411598046697L;

	public ProductRedisEntity() {}
	
	public ProductRedisEntity(ProductEntity product) {
		this.id = product.getId();
		this.price = product.getPrice();
		this.image =  product.getImage();
		this.brand =  product.getBrand();
		this.title =  product.getTitle();
		this.reviewScore =  product.getReviewScore();
	}

	@Getter
	@Setter
	private Integer id;

	@Getter
	@Setter
	private Double price;

	@Getter
	@Setter
	private String image;

	@Getter
	@Setter
	private String brand;

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private Double reviewScore;
}
