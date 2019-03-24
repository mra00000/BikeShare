/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.User;

import DAO.UserDAO;
import Model.User;
import Services.FirebaseHelper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * REST Web Service
 *
 * @author ahcl
 */



@Path("userInfo")
public class UserInfoResource {

    @Context
    private UriInfo context;
    UserDAO userDao;
    /**
     * Creates a new instance of UserInfoResource
     */
    public UserInfoResource() {
    }

    /**
     * Retrieves representation of an instance of API.User.UserInfoResource
     * @return an instance of java.lang.String
     */
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public User getInfo(
        @FormParam("token") String token,
        @FormParam("userId") int userId
    ) throws Exception {
        userDao = new UserDAO();
        if (!FirebaseHelper.checkAuthentication(token).equals("")) {
            User userInfo = userDao.getUserById(userId);
            return userInfo;
        } else {
            return null;
        }
    }
}