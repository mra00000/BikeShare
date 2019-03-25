package API.Transaction;

import DAO.TransactionDAO;
import DAO.UserDAO;
import Model.Transaction;
import Model.User;
import Services.FirebaseHelper;
import Services.GoogleOauthService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.json.JsonObject;
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

@Path("transactions")
public class Transactions {
    public static final String ERROR_TOKEN_INVALID = "-2";
    public static final String ERROR_EMAIL_NOT_FOUND = "-1";
    public static final String ERROR_AMOUNT_INSUFFICIENT = "-1";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TransactionHistoryResource
     */
    public Transactions() {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String chargeBalance(
            @FormParam("email") String email,
            @FormParam("card_number") String cardNumber,
            @FormParam("card_serial") String cardSerial
    ) throws Exception {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByEmail(email);
        if (user != null) {
            // Todo: Card verification
            int cardValue = 100;

            dao.chargeBalance(email, cardValue);
            TransactionDAO transactionDAO = new TransactionDAO();
            Transaction newTransaction =
                    new Transaction(-1, user.getId(), "charge", cardValue, new Timestamp((new Date().getTime())));
            transactionDAO.createTransaction(newTransaction);
            return String.valueOf(user.getBalance() + cardValue);
        }
        return "-1";
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String withdrawBalance(
            @FormParam("token") String token,
            @FormParam("amount") double amount
    ) throws Exception {
        String email = GoogleOauthService.getEmailByIdToken(token);
        if (email != null) {
            UserDAO dao = new UserDAO();
            User user = dao.getUserByEmail(email);
            if (user == null) {
                return ERROR_TOKEN_INVALID;
            }
            double balance = user.getBalance();
            if (amount > balance) {
                return ERROR_AMOUNT_INSUFFICIENT;
            }
            dao.chargeBalance(user.getEmail(), -amount);
            Transaction transaction = new Transaction(
                    user.getId(),
                    "withdraw",
                    amount,
                    new Timestamp(new Date().getTime())
            );
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.createTransaction(transaction);
            return String.valueOf(user.getBalance() - amount);
        } else {
            return ERROR_TOKEN_INVALID;
        }
    }
}

