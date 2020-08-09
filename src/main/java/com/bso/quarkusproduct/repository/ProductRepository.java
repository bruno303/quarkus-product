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
    public ProductEntity save(ProductEntity product) {
        persist(product);
        return product;
    }

    @Transactional
    public ProductEntity update(Long id, ProductEntity product) {
    	ProductEntity productEntity = findById(id);

        productEntity.setName(product.getName());
        productEntity.setBrand(product.getBrand());
        productEntity.setPrice(product.getPrice());
        persist(productEntity);
        return productEntity;
    }

    @Transactional
    public void remove(Long id) {
    	ProductEntity productEntity = findById(id);

        delete(productEntity);
    }
	
}
