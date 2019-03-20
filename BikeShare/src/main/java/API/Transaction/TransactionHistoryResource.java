/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Transaction;

import DAO.TransactionDAO;
import DAO.UserDAO;
import Model.Transaction;
import Model.User;
import Services.FirebaseHelper;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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

class TransactionQuery {
    private String email;
    private String token;
    private long from;
    private long to;

    public TransactionQuery() {
    }

    public TransactionQuery(String email, String token, long from, long to) {
        this.email = email;
        this.token = token;
        this.from = from;
        this.to = to;
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

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
    

    
    
    
}

@Path("transactionHistory")
public class TransactionHistoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TransactionHistoryResource
     */
    public TransactionHistoryResource() {
    }

    /**
     * Retrieves representation of an instance of API.Transaction.TransactionHistoryResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TransactionHistoryResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactionHistory (TransactionQuery query) throws Exception {
        TransactionDAO transactionDao = new TransactionDAO();
        User user = (new UserDAO()).getUserByEmail(query.getEmail());
        if (user != null) {
            if (FirebaseHelper.checkAuthentication(query.getEmail(), query.getToken())) {
                return transactionDao.getTransactionHistory(user.getId());
            }
        }
        return null;
    }
}
