/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.PostDAO;
import Model.Post;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("postInfo")
public class PostInfoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostInfoResource
     */
    public PostInfoResource() {
    }

    /**
     * Retrieves representation of an instance of API.Post.PostInfoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPostInfo(@QueryParam("postId") int postId) throws Exception {
        PostDAO postDao = new PostDAO();
        return postDao.getPostById(postId);
//        return postId + "";
    }

}
