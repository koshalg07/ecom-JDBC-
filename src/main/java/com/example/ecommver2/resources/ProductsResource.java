package com.example.ecommver2.resources;


import java.util.List;

import com.example.ecommver2.dao.ProductDAO;
import com.example.ecommver2.model.Product;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products")
public class ProductsResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return ProductDAO.getAllProducts();
	}
}
