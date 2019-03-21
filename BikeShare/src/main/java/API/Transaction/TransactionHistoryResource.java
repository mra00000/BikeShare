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
//    @POST
//    @Consumes(MediaType.F)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Transaction> getTransactionHistory () throws Exception {
//        TransactionDAO transactionDao = new TransactionDAO();
//        User user = (new UserDAO()).getUserByEmail(query.getEmail());
//        if (user != null) {
//            if (FirebaseHelper.checkAuthentication(query.getEmail(), query.getToken())) {
//                return transactionDao.getTransactionHistory(user.getId());
//            }
//        }
//        return null;
//    }
}
