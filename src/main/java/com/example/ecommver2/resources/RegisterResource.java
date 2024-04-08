package com.example.ecommver2.resources;

import com.example.ecommver2.dao.UserDAO;
import com.example.ecommver2.model.User;
import com.example.ecommver2.config.DatabaseConfig;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {
    private final UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) != null) {
            System.out.println("Username already exists");
            return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
        } else {
            userDAO.save(user);
            System.out.println("User registered");
            return Response.status(Response.Status.CREATED).entity("User registered").build();
        }
    }
}