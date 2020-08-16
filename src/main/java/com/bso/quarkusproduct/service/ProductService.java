package com.bso.quarkusproduct.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.bso.quarkusproduct.entity.ProductEntity;
import com.bso.quarkusproduct.exception.ProductNotFoundException;
import com.bso.quarkusproduct.repository.ProductRepository;

@ApplicationScoped
public class ProductService {

	private final ProductRepository productRepository;

	@Inject
	public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public ProductEntity findById(final Long id) {
		return productRepository.findById(id);
	}

	@Transactional
	public ProductEntity findByIdRequired(final Long id) {
		final ProductEntity productEntity = findById(id);
		if (productEntity == null) {
			throw new ProductNotFoundException(id);
		}

		return productEntity;
	}

	public List<ProductEntity> list() {
		return productRepository.listAll();
	}

	@Transactional
	public ProductEntity create(@Valid final ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}

	@Transactional
	public ProductEntity update(final Long id, final ProductEntity productUpdate) {
		final ProductEntity product = findByIdRequired(id);

		product.setName(productUpdate.getName());
		product.setBrand(productUpdate.getBrand());
		product.setPrice(productUpdate.getPrice());

		return productRepository.save(product);
	}

	@Transactional
	public void remove(final Long id) {
		final ProductEntity product = findByIdRequired(id);
		productRepository.remove(product);
	}
}
