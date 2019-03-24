/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.User;

import DAO.UserDAO;
import Model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import java.sql.SQLException;
import java.util.Collections;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("users")
public class UserResource {
    private static final int TOKEN_VERIFICATION_FAILED = -1;
    private static final int USER_NOT_EXIST = 0;
    private static final int USER_VALID = 1;
    private static final String API_KEY = "300209814116-ksobt2t7jm04dekiagfdj2tldshhb454.apps.googleusercontent.com";

    @Context
    private UriInfo context;
    private UserDAO userDao;

    /**
     * Creates a new instance of UserInfoResource
     */
    public UserResource() {}

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public RequestUserResult getUserInfo(
        @FormParam("token") String token
    ) throws Exception {
        RequestUserResult result = new RequestUserResult();
        HttpTransport transport = new ApacheHttpTransport();
        JsonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(API_KEY))
                .build();
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = (String) payload.get("email");
            this.userDao = new UserDAO();
            User user = this.userDao.getUserByEmail(email);
            if (user != null) {
                result.setStatus(1);
                result.setData(user);
                result.setStatus(USER_VALID);
            } else {
                result.setStatus(USER_NOT_EXIST);
            }
        } else {
            result.setStatus(TOKEN_VERIFICATION_FAILED);
        }
        return result;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(
        @FormParam("name") String name,
        @FormParam("email") String email,
        @FormParam("image") String image,
        @FormParam("phone") String phone
    ) throws Exception {
        try {
            UserResource.this.userDao = new UserDAO();
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setImage(image);
            user.setPhone(phone);
            this.userDao.addUser(user);
            return "0";
        } catch (SQLException ex) {
            return "-1";
        }
    }
}

class RequestUserResult {
    private int status;
    private User data;

    public RequestUserResult() {}

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}