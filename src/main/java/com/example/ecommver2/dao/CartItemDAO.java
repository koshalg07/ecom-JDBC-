package com.example.ecommver2.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommver2.model.CartItem;


public class CartItemDAO {
    public void addItemToCart(Connection connection, CartItem cartItem) throws SQLException {
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO cart_item (user_id, product_id, quantity) VALUES (?, ?, ?)")) {
            statement.setInt(1, cartItem.getUserId());
            statement.setInt(2, cartItem.getProductId());
            statement.setInt(3, cartItem.getQuantity());
            statement.executeUpdate();
        }
    }

    public List<CartItem> getCartItemsByUserId(Connection connection, int userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart_item WHERE user_id = ?")) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(resultSet.getInt("id"));
                    cartItem.setUserId(resultSet.getInt("user_id"));
                    cartItem.setProductId(resultSet.getInt("product_id"));
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    cartItems.add(cartItem);
                }
            }
        }
        return cartItems;
    }

    public boolean removeItemFromCart(Connection connection, int cartItemId, int productId)
            throws SQLException {
        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM cart_item WHERE id = ? AND product_id = ?")) {
            statement.setInt(1, cartItemId);
            statement.setInt(2, productId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    
}
