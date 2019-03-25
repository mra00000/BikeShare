/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Booking;

import Common.Constant;
import DAO.BookingDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import Model.Booking;
import Model.Post;
import Model.User;
import Services.FirebaseHelper;
import Services.GoogleHelper;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
    public CreateBookingResponse createBooking(
        @FormParam("token") String token,
        @FormParam("userId") int userId,
        @FormParam("postId") int postId,
        @FormParam("action") String action
    ) throws Exception {
        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userId);
        if (user == null) return new CreateBookingResponse(CreateBookingResponse.FAILED, "UserId is not valid");;
        if (GoogleHelper.authorize(token, user.getEmail())) {
            //TODO check that token 's owner is userId
            BookingDAO bookingDao = new BookingDAO();
            PostDAO postDao = new PostDAO();
            Booking booking = new Booking(userId, postId, action);
            
            Post post = postDao.getPostById(postId);
            if (post == null) {
                return new CreateBookingResponse(CreateBookingResponse.FAILED, "Post is not valid");
            } else {
                User postOwnerUser = userDao.getUserById(post.getUserId());
                double postPrice = post.getPrice();
                double userBalance = user.getBalance();
//                System.out.println(userBalance + " " + postPrice);
                if (userBalance > postPrice) {
                    user.setBalance(userBalance - postPrice);
                    postOwnerUser.setBalance(postOwnerUser.getBalance() + postPrice * (1 - Constant.PROFIT_RATE));
                    userDao.updateUser(user);
                    userDao.updateUser(postOwnerUser);
                    bookingDao.createBooking(booking);
                    return new CreateBookingResponse(CreateBookingResponse.SUCCESSED, "Successed!");
                } else {
                    return new CreateBookingResponse(CreateBookingResponse.FAILED, "Your balance is not enough!");
                }
            }
        }
        return new CreateBookingResponse(CreateBookingResponse.FAILED, "You are't authorized to access!");
    }
}

class CreateBookingResponse {
    public static int FAILED = -1;
    public static int SUCCESSED;
    private int status;
    private String message;

    public CreateBookingResponse() {
    }

    public CreateBookingResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
