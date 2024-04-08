package com.example.ecommver2.resources;

import com.example.ecommver2.model.User;
import com.example.ecommver2.config.DatabaseConfig;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            User existingUser = getUserByUsername(connection, user.getUsername());
            if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
                System.out.println("Login Successful");
                return Response.status(Response.Status.OK).entity("Login Successful").build();
            } else {
                System.out.println("Login failed");
                return Response.status(Response.Status.UNAUTHORIZED).entity("Login failed").build();
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error during login").build();
        }
    }

    private User getUserByUsername(Connection connection, String username) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }
}