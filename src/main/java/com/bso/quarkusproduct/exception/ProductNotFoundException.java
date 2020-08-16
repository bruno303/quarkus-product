package com.bso.quarkusproduct.exception;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final Long id;

	public ProductNotFoundException(final Long id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return String.format("Product %d not found!", id);
	}
}
