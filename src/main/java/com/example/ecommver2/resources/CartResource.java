package com.example.ecommver2.resources;

import java.util.List;

import com.example.ecommver2.config.DatabaseConfig;
import com.example.ecommver2.dao.CartItemDAO;
import com.example.ecommver2.dao.ProductDAO;
import com.example.ecommver2.dao.UserDAO;
import com.example.ecommver2.model.CartItem;
import com.example.ecommver2.model.Product;
import com.example.ecommver2.model.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;

@Path("/cart")
public class CartResource {
    private final CartItemDAO cartItemDAO = new CartItemDAO();
    private final UserDAO userDAO = new UserDAO();
    private final ProductDAO productDAO = new ProductDAO(); 
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemToCart(@QueryParam("username") String username,
            @QueryParam("productName") String productName) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            User user = userDAO.getUserByUsername(connection,username);
            Product product = productDAO.getProductByName( connection, productName);
            CartItem cartItem = new CartItem();
            cartItem.setUserId(user.getId());
            cartItem.setProductId(product.getId());
            cartItem.setQuantity(1);
            cartItemDAO.addItemToCart(connection, cartItem);

            return Response.status(Response.Status.CREATED).entity("Product added to the cart").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> viewCart(@QueryParam("userId") int userId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            return cartItemDAO.getCartItemsByUserId(connection, userId);
        } catch (Exception e) {
            return null;
        }
    }
    
    @DELETE
    @Path("/remove")
    public Response removeItemFromCart(@QueryParam("cartItemId") int cartItemId,
            @QueryParam("productId") int productId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            boolean isDeleted = cartItemDAO.removeItemFromCart(connection, cartItemId, productId);
            
            if (!isDeleted) {
                String message = "Cart item does not exist for the given cart item ID and product ID.";
                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
            }
            
            return Response.status(Response.Status.NO_CONTENT).entity("Cart item successfully deleted").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/checkout")
    public Response checkout() {
        return Response.status(Response.Status.OK).build();
    }
}