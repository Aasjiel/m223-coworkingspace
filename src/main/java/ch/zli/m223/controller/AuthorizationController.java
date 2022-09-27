package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

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
    public String login(LoginData loginData) {
        User user = authorizationService.getUserByUsername(loginData.getUsername());
        if (user.getPassword().equals(loginData.getPassword())){
            String token =
            Jwt.issuer("https://example.com/issuer") 
             .upn("jdoe@quarkus.io") 
             .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
             .claim("username", user.getUsername()) 
            .sign();
            System.out.println(token);
            return "Your Login was successful";
        }
        return "";
    }
}
