package br.com.magalu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.magalu.model.ProductModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
	
	public ProductEntity() {}
	
	public ProductEntity(ProductModel product) {
		this.price = product.getPrice();
		this.image = product.getImage();
		this.brand = product.getBrand();
		this.title = product.getTitle();
		this.reviewScore = product.getReviewScore();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter @Column(name="price", nullable = true)
	private Double price;
	
	@Getter @Setter @Column(name="image", nullable = true)
	private String image;
	
	@Getter @Setter @Column(name="brand", nullable = true)
	private String brand;
	
	@Getter @Setter @Column(name="title", nullable = true)
	private String title;
	
	@Getter @Setter @Column(name="reviewScore", nullable = true)
	private Double reviewScore;
	
	
}
