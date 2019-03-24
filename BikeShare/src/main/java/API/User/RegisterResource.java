/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.User;

import DAO.UserDAO;
import Model.User;
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
@Path("register")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of API.User.RegisterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(
        @FormParam("token") String token,
        @FormParam("email") String email,
        @FormParam("name") String name,
        @FormParam("phone") String phone,
        @FormParam("password") String password
    ) throws Exception {
        System.out.println("ok");
        UserDAO userDao = new UserDAO();
        if (!FirebaseHelper.checkAuthentication(token).equals("")) {
            System.out.println("ok");
            User user = new User(name, phone, email, password);
            int userId = userDao.addUser(user);
            System.out.println(userId);
            if (userId > 0) {
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
