package br.com.magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magalu.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	@Query(value = "DELETE FROM tb_customer t WHERE t.email LIKE %:email% ", nativeQuery = true)
	void deleteByEmail(@Param("email") String email);

	@Query(value = "UPDATE tb_customer t SET t.email = :email, t.nome = :nome WHERE t.email = :email ", nativeQuery = true)
	void update(@Param("email") String email, @Param("nome") String nome);

}
