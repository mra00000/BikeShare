/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.PostDAO;
import Helpers.MyStringHelpers;
import Model.Post;
import Services.FirebaseHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
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
        if (!FirebaseHelper.checkAuthentication(token).equalsIgnoreCase("")) {
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
                System.out.println(postImages);
                boolean ok = postDao.updatePost(post);
                if (ok) {
                    return post;
                }
            }
        }
        return null;
    }
}
