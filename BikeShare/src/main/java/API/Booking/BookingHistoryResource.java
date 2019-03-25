/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Booking;

import DAO.BookingDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import Model.Booking;
import Model.BookingAction;
import Model.Post;
import Model.User;
import Services.FirebaseHelper;
import Services.GoogleHelper;
import java.util.ArrayList;
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
    public List<HistoryResponse> getBookingHistory (
        @FormParam("token") String token,
        @FormParam("userId") int userId
    ) throws Exception {
        BookingDAO bookingDao = new BookingDAO();
        User user = (new UserDAO()).getUserById(userId);
        if (GoogleHelper.authorize(token, user.getEmail())) {
            List<Booking> bookings = bookingDao.getBookingHistory(userId);
            List<HistoryResponse> historyResponses = new ArrayList<>();
            for(Booking booking:bookings) {
                String phone = "";
                if (booking.getAction().equalsIgnoreCase(BookingAction.RENT)) {
                    Post post = (new PostDAO()).getPostById(booking.getPostId());
                    User postOwnerUser = (new UserDAO()).getUserById(post.getUserId());
                    phone = postOwnerUser.getPhone();
                }
                historyResponses.add(new HistoryResponse(booking.getUserId(), booking.getPostId(), booking.getAction(), phone));
            }
            return historyResponses;
        }
        return null;
    }
}

class HistoryResponse {
    private int userId, postId;
    private String action, phone;

    public HistoryResponse(int userId, int postId, String action, String phone) {
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
