package br.com.magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.magalu.entity.FavoriteProductEntity;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProductEntity, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tb_favorite_product WHERE costumer_id = :costumerId AND product_id = :productId", nativeQuery = true)
	void deleteFavoriteProductById(@Param("costumerId") String costumerId, @Param("productId") String productId);

}
