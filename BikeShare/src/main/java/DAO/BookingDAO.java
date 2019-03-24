/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Booking;
import Model.Post;
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

interface BookingStruct {
    public static final String TABLE_NAME = "share_history";
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ACTION = "action";
    public static final String POST_ID = "post_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

}
public class BookingDAO {
    Connection connection;

    public BookingDAO() throws Exception {
        this.connection = (new DbContext()).getConnection();
    }
    
    public Booking getBooking (int id) throws SQLException {
        String sql = "select * from share_history where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt(BookingStruct.USER_ID);
            String action = rs.getString(BookingStruct.ACTION);
            int postId = rs.getInt(BookingStruct.POST_ID);
            Timestamp createdTime = rs.getTimestamp(BookingStruct.CREATED_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(BookingStruct.UPDATED_AT);
            return new Booking(id, userId, postId, action, createdTime, lastUpdatedTime);
        } else return null;
    }
    
    public int createBooking (Booking booking) throws SQLException {
        String sql = "insert into share_history (user_id, action, post_id, created_at, updated_at) values(?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pre.setInt(1, booking.getUserId());
        pre.setString(2, booking.getAction());
        pre.setInt(3, booking.getPostId());
        long currentTimestamp = (new java.util.Date()).getTime();
        pre.setTimestamp(4, new Timestamp(currentTimestamp));
        pre.setTimestamp(5, new Timestamp(currentTimestamp));
        int ok = pre.executeUpdate();
        if (ok > 0) {
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }
    
    public List<Booking> getAllBooking () throws SQLException {
        List<Booking> result = new ArrayList<>();
        String sql = "select * from " + BookingStruct.TABLE_NAME;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt(BookingStruct.ID);
            int userId = rs.getInt(BookingStruct.USER_ID);
            String action = rs.getString(BookingStruct.ACTION);
            int postId = rs.getInt(BookingStruct.POST_ID);
            Timestamp createdTime = rs.getTimestamp(BookingStruct.CREATED_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(BookingStruct.UPDATED_AT);
            result.add(new Booking(id, userId, postId, action, createdTime, lastUpdatedTime));
        }
        return result;
    }
    
    public List<Booking> getBookingHistory (int userId) throws SQLException {
        List<Booking> result = new ArrayList<>();
        String sql = "select * from share_history where user_id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, userId);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(BookingStruct.ID);
            String action = rs.getString(BookingStruct.ACTION);
            int postId = rs.getInt(BookingStruct.POST_ID);
            Timestamp createdTime = rs.getTimestamp(BookingStruct.CREATED_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(BookingStruct.UPDATED_AT);
            result.add(new Booking(id, userId, postId, action, createdTime, lastUpdatedTime));
        }
        return result;
    }
    
    
    public static void main(String[] args) throws Exception {
        BookingDAO dao = new BookingDAO();
        try {
            List<Booking> list = dao.getBookingHistory(2);
            System.out.println(list.get(0).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
