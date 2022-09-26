package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;

@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Users.", description = "Returns a list of all users.")
    public List<User> getAlUsers() {
            return userService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one User", description = "Returns a User.")
    @Path("/{id}")
    public List<User> index(Long id) {   
            return List.of(userService.getUserById(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new User.", description = "Creates a new user and returns the newly added entry.")
    public User create(User user) {
       return userService.createUser(user);
    }

    @DELETE
    @Operation(
        summary = "Deletes a User",
        description = "Deletes a specified User and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        userService.deleteUser(id);
    }

    @PUT
    @Operation(
        summary = "Updates a User",
        description = "Updates a user specified by the id"
    )
    @Path("/{id}")
    public void update(Long id, User user){
        user.setId(id);
        userService.updateUser(user);
    }
}
