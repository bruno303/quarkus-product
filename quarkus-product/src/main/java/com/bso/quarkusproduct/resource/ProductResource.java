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
import com.bso.quarkusproduct.repository.ProductRepository;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	private final ProductRepository productRepository;

	@Inject
	public ProductResource(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	@GET
    public List<ProductEntity> list() {
        return productRepository.listAll();
    }

    @POST
    public Response create(@Valid ProductEntity product) {
    	ProductEntity productEntity = productRepository.save(product);
        return Response.ok(productEntity).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, ProductEntity product) {
    	ProductEntity productUpdated = productRepository.update(id, product);

        return Response.ok(productUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        productRepository.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
