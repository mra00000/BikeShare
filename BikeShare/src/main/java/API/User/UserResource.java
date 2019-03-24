/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.User;

import DAO.UserDAO;
import Model.User;
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
import java.util.Collections;
import java.util.Map;

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
    private static final String API_KEY = "300209814116-ksobt2t7jm04dekiagfdj2tldshhb454.apps.googleusercontent.com\n";

    @Context
    private UriInfo context;
    private UserDAO userDao;

    /**
     * Creates a new instance of UserInfoResource
     */
    public UserResource() {}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RequestUserResult getUserInfo(
        @QueryParam("token") String token
    ) throws Exception {
        System.out.println("ahihi");
        System.out.println(token);
        System.out.println("ahihi");
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
            UserDAO dao = new UserDAO();
            User user = dao.getUserByEmail(email);
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