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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ahcl
 */
@Path("withdraw")
public class WithdrawResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WithdrawResource
     */
    public WithdrawResource() {
    }

    /**
     * Retrieves representation of an instance of API.User.WithdrawResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdraw(@FormParam("token") String token, @FormParam("amount") double amount, @FormParam("email") String email) throws Exception {
        UserDAO userDao = new UserDAO();
        if (!FirebaseHelper.checkAuthentication(token).equalsIgnoreCase("")) {
            
            User user = userDao.getUserByEmail(email);
            double balance = user.getBalance();
            if (amount > 0 && balance > amount) {
                user.setBalance(balance - amount);
                userDao.updateUser(user);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
