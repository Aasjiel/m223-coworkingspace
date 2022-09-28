package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.smallrye.jwt.build.Jwt;

import ch.zli.m223.dto.LoginData;
import ch.zli.m223.service.AuthorizationService;
import ch.zli.m223.model.User;

@Path("/auth")
public class AuthorizationController {

    @Inject
    AuthorizationService authorizationService;
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(LoginData loginData) {
        User user = authorizationService.getUserByUsername(loginData.getUsername());
        if (user.getPassword().equals(loginData.getPassword())){
            String token =
            Jwt.issuer("https://example.com/issuer") 
             .upn(user.getUsername()) 
             .groups(user.getRole().getName()) 
             .claim("username", user.getUsername())
             .claim("id", user.getId())
            .sign();
            return Response.ok(token).build();
        }
        return Response.status(Status.BAD_REQUEST).entity("wrong credentials").build();
    }
}
