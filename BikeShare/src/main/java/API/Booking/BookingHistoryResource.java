/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Booking;

import DAO.BookingDAO;
import DAO.UserDAO;
import Model.Booking;
import Model.User;
import Services.FirebaseHelper;
import Services.GoogleHelper;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ahcl
 */

@Path("bookingHistory")
public class BookingHistoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookingHistoryResource
     */
    public BookingHistoryResource() {
    }

    /**
     * Retrieves representation of an instance of API.Booking.BookingHistoryResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookingHistory (
        @FormParam("token") String token,
        @FormParam("userId") int userId
    ) throws Exception {
        BookingDAO bookingDao = new BookingDAO();
        User user = (new UserDAO()).getUserById(userId);
        if (GoogleHelper.authorize(token, user.getEmail())) {
//            System.out.println(userId);
            List<Booking> bookings = bookingDao.getBookingHistory(userId);
            return bookings;
        }
        return null;
    }
}
