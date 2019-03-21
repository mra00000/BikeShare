/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author ahcl
 */
public class UserDAO {
    Connection connection;

    public UserDAO() throws Exception {
        this.connection = (new DbContext()).getConnection();
    }
    
    public User getUserByEmail (String email) throws SQLException {
        String sql = "select * from Users where email = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String password = rs.getString("password");
            double balance = rs.getDouble("balance");
            Timestamp createTime = rs.getTimestamp("created_at");
            Timestamp lastUpdatedTime = rs.getTimestamp("updated_at");
            return new User(id, name, phone, email, password, balance, createTime, lastUpdatedTime);
        } else return null;
    }
    
    public User getUserById (int id) throws SQLException {
        String sql = "select * from Users where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String password = rs.getString("password");
            String email = rs.getString("email");
            double balance = rs.getDouble("balance");
            Timestamp createTime = rs.getTimestamp("created_at");
            Timestamp lastUpdatedTime = rs.getTimestamp("updated_at");
            return new User(id, name, phone, email, password, balance, createTime, lastUpdatedTime);
        } else return null;
    }
    
    public void addUser (User user) throws SQLException {
        String sql = "insert into Users(name, email, password, phone, balance, created_at, updated_at) values(?,?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, user.getName());
        pre.setString(2, user.getEmail());
        pre.setString(3, user.getPassword());
        pre.setString(4, user.getPhone());
        pre.setDouble(5, 0.0);
        long currentTimestamp = (new java.util.Date()).getTime();
        pre.setTimestamp(6, new Timestamp(currentTimestamp));
        pre.setTimestamp(7, new Timestamp(currentTimestamp));
        pre.execute();
    }
    
    public boolean updateUser (User user) throws SQLException {
        String sql = "update users "
                + "set name = ?, email = ?, password = ?, balance = ?, created_at = ?, updated_at = ? "
                + "where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, user.getName());
        pre.setString(2, user.getEmail());
        pre.setString(3, user.getPassword());
        pre.setDouble(4, user.getBalance());
        pre.setTimestamp(5, user.getCreatedTime());
        pre.setTimestamp(6, user.getLastUpdatedTime());
        pre.setInt(7, user.getId());
        boolean ok = pre.execute();
        return ok;
    }
    
    public boolean isExisted (String email) throws SQLException {
        return (this.getUserByEmail(email) != null);
    }
    
    public static void main(String[] args) throws Exception {
        UserDAO dao = new UserDAO();
        try {
            long time = (new java.util.Date()).getTime();
            User user = new User(2, "duong", "0932585101", "ahcl@gmail.com", "123122", 123122.0, new Timestamp(time), new Timestamp(time));
            dao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
