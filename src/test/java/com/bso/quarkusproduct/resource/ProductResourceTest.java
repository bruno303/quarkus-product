package com.bso.quarkusproduct.resource;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductResourceTest {
	
	@Test
    public void testProductEndpoint() {
        given()
          .when().get("/products")
          .then()
             .statusCode(200);
    }

}
