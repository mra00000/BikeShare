/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Post;

import DAO.PostDAO;
import Model.Post;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("posts")
public class PostsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostsResource
     */
    public PostsResource() {
    }

    /**
     * Retrieves representation of an instance of API.Post.PostsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPosts(
        @QueryParam("page") int page,
        @QueryParam("pageSize") int size
    ) throws Exception {
        //TODO return proper representation object
        PostDAO postDao = new PostDAO();
        List<Post> posts = postDao.getPostsByPage(page, size);
        return posts;
    }

}