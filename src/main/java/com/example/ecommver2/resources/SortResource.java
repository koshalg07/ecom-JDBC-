package com.example.ecommver2.resources;


import java.util.List;

import com.example.ecommver2.config.DatabaseConfig;
import com.example.ecommver2.dao.ProductDAO;
import com.example.ecommver2.model.Product;
import com.google.gson.Gson;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;

@Path("/sort")
public class SortResource {
    private final ProductDAO productDAO = new ProductDAO();
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortProductsByPrice() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            List<Product> products = productDAO.getAllProducts(connection);

            products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

            String json = gson.toJson(products);

            return Response.ok(json).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sorting products").build();
        }
    }
}