package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.service.BookingService;
import ch.zli.m223.model.Booking;

@Path("/bookings")
public class BookingController {
    @Inject
    BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Bookings.", description = "Returns a list of all Bookings.")
    public List<Booking> getAllBookings() {
            return bookingService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one Bookings", description = "Returns a Bookings.")
    @Path("/{id}")
    public Booking index(Long id) {   
            if(bookingService.getBookingById(id) != null) {
                return bookingService.getBookingById(id);
            } 
            throw new BadRequestException();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Bookings.", description = "Creates a new Bookings and returns the newly added entry.")
    public Booking create(Booking booking) {
       return bookingService.createBooking(booking);
    }

    @DELETE
    @Operation(
        summary = "Deletes a Bookings",
        description = "Deletes a specified Bookings and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        bookingService.deleteBooking(id);
    }

    @PUT
    @Operation(
        summary = "Updates a Booking",
        description = "Updates a specified Booking and returns not a single thing"
    )
    @Path("/{id}")
    public void update(Long id, Booking booking){
        booking.setId(id);
        bookingService.updateBooking(booking);
    }
}
