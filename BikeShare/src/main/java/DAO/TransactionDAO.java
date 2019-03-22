/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Booking;
import Model.Post;
import Model.Transaction;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahcl
 */

interface TransactionStruct {
    public static final String TABLE_NAME = "transactions";
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ACTION = "action";
    public static final String AMOUNT = "amount";
    public static final String CREATED_AT = "created_at";

}
public class TransactionDAO {
    Connection connection;

    public TransactionDAO() throws Exception {
        this.connection = (new DbContext()).getConnection();
    }
    
    public List<Transaction> getTransactionHistory (int userId) throws SQLException {
        List<Transaction> result = new ArrayList<>();
        String sql = "select * from transactions where user_id = ? order by created_at";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, userId);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(TransactionStruct.ID);
            String action = rs.getString(TransactionStruct.ACTION);
            Timestamp createdTime = rs.getTimestamp(TransactionStruct.CREATED_AT);
            double amount = rs.getDouble(TransactionStruct.AMOUNT);
            result.add(new Transaction(id, userId, action, amount, createdTime));
        }
        return result;
    }
    
    public void createTransaction (Transaction transaction) throws SQLException {
        String sql = "insert into transactions (user_id, action, amount, created_at) values(?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, transaction.getUserId());
        pre.setString(2, transaction.getAction());
        pre.setDouble(3, transaction.getAmount());
        long currentTimestamp = (new java.util.Date()).getTime();
        pre.setTimestamp(4, new Timestamp(currentTimestamp));
        pre.execute();
    }
    
    
//    public boolean isExisted (String email) throws SQLException {
//        return (this.getUser(email) != null);
//    }
    
//    public List<Booking> getTransactionHistory () throws SQLException {
//        List<Booking> result = new ArrayList<>();
//        String sql = "select * from " + BookingStruct.TABLE_NAME;
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()) {
//            int id = rs.getInt(BookingStruct.ID);
//            int userId = rs.getInt(BookingStruct.USER_ID);
//            String action = rs.getString(BookingStruct.ACTION);
//            int postId = rs.getInt(BookingStruct.POST_ID);
//            Timestamp createdTime = rs.getTimestamp(PostStruct.CREATE_AT);
//            Timestamp lastUpdatedTime = rs.getTimestamp(PostStruct.UPDATED_AT);
//            result.add(new Booking(id, userId, postId, action, createdTime, lastUpdatedTime));
//        }
//        return result;
//    }
    
    
    public static void main(String[] args) throws Exception {
        TransactionDAO dao = new TransactionDAO();
        try {
            List<Transaction> transactions = dao.getTransactionHistory(3);
            for(Transaction t:transactions) {
                System.out.println(t.getUserId() + " " + t.getAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
