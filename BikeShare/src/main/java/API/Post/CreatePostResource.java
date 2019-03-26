/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.BookingDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import Helpers.MyStringHelpers;
import Model.Booking;
import Model.BookingAction;
import Model.Post;
import Model.User;
import Services.FirebaseHelper;
import Services.GoogleHelper;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("createPost")
public class CreatePostResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreatePostResource
     */
    public CreatePostResource() {
    }

    /**
     * Retrieves representation of an instance of API.Post.CreatePostResource
     *
     * @param userId 
     * @param title
     * @param description
     * @param price
     * @param token
     * @return an instance of Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Post createPost(
            @FormParam("userId") int userId,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("price") double price,
            @FormParam("token") String token,
            @FormParam("images") String images
    ) throws Exception {
        User user = (new UserDAO()).getUserById(userId);
        if (GoogleHelper.authorize(token, user.getEmail())) {
            PostDAO postDao = new PostDAO();
            Post post = new Post(userId, title, description, "", price);
            int postId = postDao.createPost(post);
            System.out.println(postId);
            if (postId > 0) {
                List<String> base64Images = MyStringHelpers.toList(images, "|");
                System.out.println(base64Images.size());
                String postImages = "";
                for (int i = 0; i < base64Images.size(); i++) {
                    System.out.println(base64Images.get(i));
                    String url = postDao.uploadImage(base64Images.get(i), postId + "_" + (i + 1) + ".png");
                    if (!url.equals("")) {
                        postImages = postImages.concat("|").concat(url);
                    }
                }
                post = postDao.getPostById(postId);
                post.setImages(postImages);
                Booking booking = new Booking(userId, postId, BookingAction.POST);
                (new BookingDAO()).createBooking(booking);
                boolean ok = postDao.updatePost(post);
                if (ok) {
                    return post;
                }
            }
        }
        return null;
    }
}
