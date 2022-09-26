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

import ch.zli.m223.service.PlaceService;
import ch.zli.m223.model.Place;

@Path("/places")
public class PlaceController {
    @Inject
    PlaceService placeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Places.", description = "Returns a list of all Places.")
    public List<Place> getAllPlaces() {
            return placeService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one Places", description = "Returns a Places.")
    @Path("/{id}")
    public List<Place> index(Long id) {   
            return List.of(placeService.getPlaceById(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Places.", description = "Creates a new Places and returns the newly added entry.")
    public Place create(Place place) {
       return placeService.createPlace(place);
    }

    @DELETE
    @Operation(
        summary = "Deletes a Places",
        description = "Deletes a specified Places and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        placeService.deletePlace(id);
    }

    @PUT
    @Path("/{id}")
    public void update(Long id, Place place){
        place.setId(id);
        placeService.updatePlace(place);
    }
}
