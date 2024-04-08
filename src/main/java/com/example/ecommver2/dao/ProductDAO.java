package com.example.ecommver2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommver2.config.DatabaseConfig;
import com.example.ecommver2.model.Product;

public class ProductDAO {
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");

                Product product = new Product(id, name, price,category, description);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }
    
    public List<Product> searchProductsByNameAndCategory(Connection connection, String name, String category)
            throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM products WHERE name = ? AND category = ?")) {
            statement.setString(1, name);
            statement.setString(2, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    public List<Product> searchProductByName(Connection connection, String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    public List<Product> searchProductByCategory(Connection connection, String category) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE category = ?")) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    public List<Product> getAllProducts(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    products.add(product);
                }
            }
        }
        return products;
    }
    public List<Product> searchProductsByCharacter(Connection connection, String character) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE name LIKE ? OR category LIKE ?")) {
            statement.setString(1, "%" + character + "%");
            statement.setString(2, "%" + character + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setPrice(resultSet.getDouble("price"));
                    products.add(product);
                }
            }
        }
        return products;
    }
    
    public Product getProductByName(Connection connection, String productName) throws SQLException {
        String query = "SELECT * FROM products WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    return product;
                }
            }
        }
        return null; 
    }
    
    public Product getProductById(Connection connection, int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDescription(resultSet.getString("description"));
                    return product;
                }
            }
        }
        return null;
    }
    
}
