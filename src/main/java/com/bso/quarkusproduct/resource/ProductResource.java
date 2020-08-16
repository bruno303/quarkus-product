package com.bso.quarkusproduct.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bso.quarkusproduct.entity.ProductEntity;
import com.bso.quarkusproduct.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	private final ProductService productService;

	@Inject
	public ProductResource(final ProductService productService) {
		super();
		this.productService = productService;
	}

	@GET
	public List<ProductEntity> list() {
		return productService.list();
	}

	@POST
	public Response create(@Valid final ProductEntity product) {
		final ProductEntity productEntity = productService.create(product);
		return Response.ok(productEntity).status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") final Long id, final ProductEntity product) {
		final ProductEntity productUpdated = productService.update(id, product);

		return Response.ok(productUpdated).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") final Long id) {
		productService.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
