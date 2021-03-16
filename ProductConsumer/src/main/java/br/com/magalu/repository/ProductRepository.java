package br.com.magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.magalu.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE tb_product t SET t.title = :title WHERE t.id = :id ", nativeQuery = true)
	void updateNameById(@Param("id") Integer id, @Param("title") String title);

}
