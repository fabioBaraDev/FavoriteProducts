package br.com.magalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magalu.entity.FavoriteProductEntity;
import br.com.magalu.model.FavoriteProductModel;
import br.com.magalu.repository.FavoriteProductRepository;
import br.com.magalu.service.FavoriteProductService;

@Service
public class FavoriteProductServiceImpl implements FavoriteProductService {

	@Autowired
	private FavoriteProductRepository repository;

	@Override
	public void save(FavoriteProductModel favoriteProductModel) {

		var entity = new FavoriteProductEntity();
		entity.setCostumerId(favoriteProductModel.getClientId());
		entity.setProductId(favoriteProductModel.getProductId());
		repository.save(entity);
	}

	@Override
	public void delete(FavoriteProductModel favoriteProductModel) {

		repository.deleteFavoriteProductById(
				favoriteProductModel.getClientId().toString(),
				favoriteProductModel.getProductId().toString());

	}

}
