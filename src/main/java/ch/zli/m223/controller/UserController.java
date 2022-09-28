package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.hibernate.event.spi.ResolveNaturalIdEvent;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;

@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @RolesAllowed({"Admin", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Users.", description = "Returns a list of all users.")
    public List<User> getAlUsers() {
            return userService.findAll();
    }

    @GET
    @RolesAllowed({"Admin", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one User", description = "Returns a User.")
    @Path("/{id}")
    public Response index(Long id) {   
        if(id > 0 && userService.getUserById(id) != null) {
            return Response.ok(userService.getUserById(id)).build();
        } 
        return Response.status(Status.BAD_REQUEST).entity("you need to provide an id").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new User.", description = "Creates a new user and returns the newly added entry.")
    public Response create(User user) {
       try {
        return Response.ok(userService.createUser(user)).build();
        }   catch (ConstraintViolationException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getConstraintViolations().toString()).build();
        }
    }

    @DELETE
    @RolesAllowed("Admin")
    @Operation(
        summary = "Deletes a User",
        description = "Deletes a specified User and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        userService.deleteUser(id);
    }

    @PUT
    @RolesAllowed("Admin")
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
