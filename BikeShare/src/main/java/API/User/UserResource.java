/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.User;

import DAO.UserDAO;
import Model.User;
import Services.FirebaseHelper;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author ahcl
 */

class userInfoQuery {
    private String email, token;

    public userInfoQuery() {
    }

    public userInfoQuery(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
@Path("userInfo")
public class UserResource {

    @Context
    private UriInfo context;
    UserDAO userDao;
    /**
     * Creates a new instance of UserInfoResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of API.User.UserInfoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(new userInfoQuery("asdf", "asdf"));
        //TODO return proper representation object
   //     throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserInfoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User getInfo(userInfoQuery query) throws Exception {
        userDao = new UserDAO();
        String email = query.getEmail();
        String token = query.getToken();
        if (FirebaseHelper.checkAuthentication(email, token)) {
            User userInfo = userDao.getUserByEmail(email);
            return userInfo;
        } else {
            return null;
            // may return status + message
        }
    }
}
