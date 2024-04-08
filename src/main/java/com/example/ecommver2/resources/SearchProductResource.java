package com.example.ecommver2.resources;


import java.util.List;

import com.example.ecommver2.config.DatabaseConfig;
import com.example.ecommver2.dao.ProductDAO;
import com.example.ecommver2.model.Product;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.Connection;

@Path("/search")
public class SearchProductResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> searchProduct(@QueryParam("name") String name, @QueryParam("category") String category, @QueryParam("character") String character) {
	    ProductDAO productDAO = new ProductDAO();
		try (Connection connection = DatabaseConfig.getConnection()) {
	        if (name != null && !name.isEmpty() && category != null && !category.isEmpty()) {
	            return productDAO.searchProductsByNameAndCategory(connection, name, category);
	        } else if (name != null && !name.isEmpty()) {
	            return productDAO.searchProductByName(connection, name);
	        } else if (category != null && !category.isEmpty()) {
	            return productDAO.searchProductByCategory(connection, category);
	        } else if (character != null && character.length() == 1) {
	            return productDAO.searchProductsByCharacter(connection, character);
	        } else {
	            return productDAO.getAllProducts(connection);
	        }
	    } catch (Exception e) {
	        return null;
	    }
	}
}