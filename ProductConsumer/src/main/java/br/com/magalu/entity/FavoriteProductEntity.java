package br.com.magalu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_favorite_product")
@EntityListeners(AuditingEntityListener.class)
public class FavoriteProductEntity {
	
	public FavoriteProductEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@Column(name="costumer_id", nullable = false)
	@Getter @Setter
	private Integer costumerId;
	
	@Column(name="product_id", nullable = false)
	@Getter @Setter
	private Integer productId;
}
