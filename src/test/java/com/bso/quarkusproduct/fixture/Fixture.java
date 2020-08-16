package com.bso.quarkusproduct.fixture;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.bso.quarkusproduct.entity.ProductEntity;
import com.bso.quarkusproduct.repository.ProductRepository;

@ApplicationScoped
public class Fixture {

	private final ProductRepository productRepository;

	@Inject
	public Fixture(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductEntity createProduct() {
		final ProductEntity product = new ProductEntity();
		product.setBrand("brand");
		product.setModelYear(LocalDate.now());
		product.setName("product");
		product.setPrice(BigDecimal.TEN);

		final ProductEntity productCreated = productRepository.save(product);
		return productCreated;
	}

}
