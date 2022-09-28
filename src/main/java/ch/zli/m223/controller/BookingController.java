package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.service.BookingService;
import ch.zli.m223.model.Booking;

@Path("/bookings")
public class BookingController {
    @Inject
    BookingService bookingService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"Admin", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Bookings.", description = "Returns a list of all Bookings.")
    public List<Booking> getAllBookings() {
            return bookingService.findAll();
    }

    @GET
    @RolesAllowed({"Admin", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one Bookings", description = "Returns a Bookings.")
    @Path("/{id}")
    public Response index(Long id) {   
            Booking booking = bookingService.getBookingById(id);
            if(bookingService.getBookingById(id) != null) {
                if (jwt.getClaim("id").toString().equals(booking.getUser().getId().toString()) || jwt.getGroups().contains("Admin")) {
                    return Response.ok(booking).build();
                }
                return Response.status(Status.UNAUTHORIZED).entity("This Booking is not one of yours").build();
            } 
            return Response.status(Status.UNAUTHORIZED).build();
    }

    @POST
    @RolesAllowed({"Admin","Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Bookings.", description = "Creates a new Bookings and returns the newly added entry.")
    public Booking create(Booking booking) {
       return bookingService.createBooking(booking);
    }

    @DELETE
    @RolesAllowed("Admin")
    @Operation(
        summary = "Deletes a Bookings",
        description = "Deletes a specified Bookings and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        bookingService.deleteBooking(id);
    }

    @PUT
    @RolesAllowed({"Admin","Mitglied"})
    @Operation(
        summary = "Updates a Booking",
        description = "Updates a specified Booking and returns not a single thing"
    )
    @Path("/{id}")
    public Response update(@Context SecurityContext ctx, Long id, Booking booking){
        if (jwt.getClaim("id").toString().equals(booking.getUser().getId().toString()) || jwt.getGroups().contains("Admin")) {
            booking.setId(id);
            bookingService.updateBooking(booking);
            return Response.ok(booking).build();
        }

        return Response.status(Status.UNAUTHORIZED).entity("you are not allowed to update this booking").build();
        
    }
}
