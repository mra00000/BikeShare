/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.PostDAO;
import Model.Post;
import Services.FirebaseHelper;
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
    public Response createPost(
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
                String[] base64Images = images.split("[|]");
                String postImages = "";
                for (int i = 0; i < base64Images.length; i++) {
                    String url = postDao.uploadImage(base64Images[i], postId + "_" + (i + 1) + ".png");
                    System.out.println(url);
                    if (!url.equals("")) {
                        postImages = postImages.concat("|").concat(url);
                    }
                }
                post = postDao.getPostById(postId);
                post.setImages(postImages);
                boolean ok = postDao.updatePost(post);
                if (ok) {
                    return Response.ok().build();
                }
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
