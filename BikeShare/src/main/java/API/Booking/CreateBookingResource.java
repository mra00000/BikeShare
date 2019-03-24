/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Booking;

import DAO.BookingDAO;
import Model.Booking;
import Services.FirebaseHelper;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("createBooking")
public class CreateBookingResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreateBookingResource
     */
    public CreateBookingResource() {
    }

    /**
     * Retrieves representation of an instance of API.Booking.CreateBookingResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(
        @FormParam("token") String token,
        @FormParam("userId") int userId,
        @FormParam("postId") int PostId,
        @FormParam("action") String action
    ) throws Exception {
        if (!FirebaseHelper.checkAuthentication(token).equals("")) {
            //TODO check that token 's owner is userId
            BookingDAO bookingDao = new BookingDAO();
            Booking booking = new Booking(userId, PostId, action);
            int id = bookingDao.createBooking(booking);
            if (id > 0) {
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
