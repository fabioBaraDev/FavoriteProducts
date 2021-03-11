package br.com.magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.magalu.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tb_customer WHERE email = :email ", nativeQuery = true)
	void deleteByEmail(@Param("email") String email);

	@Modifying
	@Transactional
	@Query(value = "UPDATE tb_customer t SET t.nome = :nome WHERE t.email = :email ", nativeQuery = true)
	void update(@Param("email") String email, @Param("nome") String nome);

}
