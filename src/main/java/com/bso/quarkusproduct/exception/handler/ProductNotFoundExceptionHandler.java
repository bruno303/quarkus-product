package com.bso.quarkusproduct.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bso.quarkusproduct.exception.ProductNotFoundException;
import com.bso.quarkusproduct.rest.util.ApiResponse;

@Provider
public class ProductNotFoundExceptionHandler implements ExceptionMapper<ProductNotFoundException> {

	@Override
	public Response toResponse(final ProductNotFoundException exception) {
		final var response = new ApiResponse<>(exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(response).build();
	}

}