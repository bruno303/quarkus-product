package com.bso.quarkusproduct.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import com.bso.quarkusproduct.entity.ProductEntity;
import com.bso.quarkusproduct.exception.ProductNotFoundException;
import com.bso.quarkusproduct.fixture.Fixture;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Transactional
public class ProductServiceTest {

	@Inject
	private ProductService productService;

	@Inject
	private Fixture fixture;

	@Test
	public void testCreate() {
		final ProductEntity product = new ProductEntity();
		product.setBrand("brand");
		product.setModelYear(LocalDate.now());
		product.setName("product");
		product.setPrice(BigDecimal.TEN);

		final ProductEntity productCreated = productService.create(product);
		assertNotNull(productCreated);
		assertTrue(productCreated.getId() > 0);
		assertEquals(product.getBrand(), productCreated.getBrand());
		assertEquals(product.getCreatedDateTime(), productCreated.getCreatedDateTime());
		assertEquals(product.getModelYear(), productCreated.getModelYear());
		assertEquals(product.getName(), productCreated.getName());
		assertEquals(0, product.getPrice().compareTo(productCreated.getPrice()));
	}

	@Test
	public void testFindByIdRequired() {
		final ProductEntity product = fixture.createProduct();
		final ProductEntity productFromDatabase = productService.findByIdRequired(product.getId());

		assertNotNull(productFromDatabase);
		assertEquals(product.getId(), productFromDatabase.getId());
		assertEquals(product.getBrand(), productFromDatabase.getBrand());
		assertEquals(product.getCreatedDateTime(), productFromDatabase.getCreatedDateTime());
		assertEquals(product.getModelYear(), productFromDatabase.getModelYear());
		assertEquals(product.getName(), productFromDatabase.getName());
		assertEquals(0, product.getPrice().compareTo(productFromDatabase.getPrice()));
	}

	@Test
	public void testUpdate() {
		final ProductEntity product = fixture.createProduct();
		product.setName("product updated");
		productService.update(product.getId(), product);

		final ProductEntity productFromDatabase = productService.findByIdRequired(product.getId());

		assertNotNull(productFromDatabase);
		assertEquals(product.getId(), productFromDatabase.getId());
		assertEquals(product.getBrand(), productFromDatabase.getBrand());
		assertEquals(product.getCreatedDateTime(), productFromDatabase.getCreatedDateTime());
		assertEquals(product.getModelYear(), productFromDatabase.getModelYear());
		assertEquals(product.getName(), productFromDatabase.getName());
		assertEquals(0, product.getPrice().compareTo(productFromDatabase.getPrice()));
	}

	@Test
	public void testRemove() {
		final ProductEntity product = fixture.createProduct();
		productService.remove(product.getId());
		final ProductEntity productFromDatabase = productService.findById(product.getId());
		assertNull(productFromDatabase);
	}

	@Test
	public void testRemoveProductNotFound() {
		assertThrows(ProductNotFoundException.class, () -> {
			productService.remove(Long.MAX_VALUE);
		});
	}

	@Test
	public void testUpdateInProductNotFound() {
		final ProductEntity product = fixture.createProduct();
		assertThrows(ProductNotFoundException.class, () -> {
			productService.update(Long.MAX_VALUE, product);
		});
	}
}
