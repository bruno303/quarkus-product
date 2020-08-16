package com.bso.quarkusproduct.rest.util;

public class ApiResponse<T> {

	private final T data;
	private final String error;

	public ApiResponse(final T data) {
		this.data = data;
		this.error = null;
	}

	public ApiResponse(final String error) {
		this.error = error;
		this.data = null;
	}

	public T getData() {
		return data;
	}

	public String getError() {
		return error;
	}
}
