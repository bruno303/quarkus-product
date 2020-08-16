package com.bso.quarkusproduct.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.bso.quarkusproduct.entity.ProductEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {

	public List<ProductEntity> list() {
		return listAll();
	}

	@Transactional
	public ProductEntity save(final ProductEntity product) {
		persist(product);
		return product;
	}

	@Transactional
	public void remove(final ProductEntity productEntity) {
		delete(productEntity);
	}

}
