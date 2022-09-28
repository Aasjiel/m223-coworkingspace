package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
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

import ch.zli.m223.service.PlaceService;
import ch.zli.m223.model.Place;

@Path("/places")
public class PlaceController {
    @Inject
    PlaceService placeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Places.", description = "Returns a list of all Places.")
    public Response getAllPlaces() {
            return Response.ok(placeService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one Places", description = "Returns a Places.")
    @Path("/{id}")
    public Response index(Long id) {   
            if(id > 0 && placeService.getPlaceById(id) != null) {
                return Response.ok(placeService.getPlaceById(id)).build();
            } 
           return Response.status(Status.BAD_REQUEST).entity("you need to provide an id that exists").build();
    }

    @POST
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Places.", description = "Creates a new Places and returns the newly added entry.")
    public Response create(Place place) {
        try {
            return Response.ok(placeService.createPlace(place)).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getConstraintViolations().toString()).build();
        }
       
    }

    @DELETE
    @RolesAllowed("Admin")
    @Operation(
        summary = "Deletes a Places",
        description = "Deletes a specified Places and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        placeService.deletePlace(id);
    }

    @PUT
    @RolesAllowed("Admin")
    @Operation(
        summary = "Updates a Place",
        description = "Updates a specified Place and returns not a single thing"
    )
    @Path("/{id}")
    public void update(Long id, Place place){
        place.setId(id);
        placeService.updatePlace(place);
    }
}
