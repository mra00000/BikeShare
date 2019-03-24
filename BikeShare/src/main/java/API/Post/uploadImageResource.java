/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.PostDAO;
import Model.Post;
import Services.FirebaseHelper;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
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
import sun.misc.BASE64Decoder;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("uploadImage")
public class uploadImageResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ImageResource
     */
    public uploadImageResource() {
    }

    /**
     * Retrieves representation of an instance of API.Post.ImageResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLoadImage(
            @FormParam("token") String token,
            @FormParam("image") String base64Image,
            @FormParam("postId") int postId
    )throws IOException, Exception {
        PostDAO postDao = new PostDAO();
        Post post = postDao.getPostById(postId);
        if (post == null) return Response.status(Response.Status.BAD_REQUEST).build();
        int index = post.getImages().split("[|]").length;
        String url = postDao.uploadImage(base64Image, postId + "_" + index + ".png");
        if (!url.equals("")) post.setImages(post.getImages().concat("|").concat(url));
        postDao.updatePost(post);
        return Response.ok().build();
    }
    
}
