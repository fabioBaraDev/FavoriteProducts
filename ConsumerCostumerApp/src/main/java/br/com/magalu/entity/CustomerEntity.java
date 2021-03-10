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
@Table(name = "tb_customer")
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;

	@Column(name="nome", nullable = false)
	@Getter @Setter
	private String nome;

	@Column(name="email", nullable = false)
	@Getter @Setter
	private String email;
}
