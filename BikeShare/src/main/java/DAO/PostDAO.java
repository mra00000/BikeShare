/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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

interface PostStruct {
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGES = "images";
    public static final String PRICE = "price";
    public static final String CREATE_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

}
public class PostDAO {
    Connection connection;

    public PostDAO() throws Exception {
        this.connection = (new DbContext()).getConnection();
    }
    
    public Post getPostById (int id) throws SQLException {
        String sql = "select * from Posts where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt(PostStruct.USER_ID);
            String title = rs.getString(PostStruct.TITLE);
            String description = rs.getString(PostStruct.DESCRIPTION);
            String images = rs.getString(PostStruct.IMAGES);
            double price = rs.getDouble(PostStruct.PRICE);
            Timestamp createTime = rs.getTimestamp(PostStruct.CREATE_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(PostStruct.UPDATED_AT);
            return new Post(id, userId, title, description, images, price, createTime, lastUpdatedTime);
        } else return null;
    }
    
    public List<Post> getPosts (int from, int to) throws SQLException {
        String sql = "SELECT  *\n" +
                        "FROM    ( SELECT ROW_NUMBER() OVER ( ORDER BY updated_at DESC) AS RowNum, *\n" +
                        "          FROM      posts\n" +
                        "        ) AS RowConstrainedResult\n" +
                        "WHERE   RowNum >= ?\n" +
                        "    AND RowNum < ?\n" +
                        "ORDER BY RowNum";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, from);
        pre.setInt(2, to);
        List<Post> result = new ArrayList<>();
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(PostStruct.ID);
            int userId = rs.getInt(PostStruct.USER_ID);
            String title = rs.getString(PostStruct.TITLE);
            String description = rs.getString(PostStruct.DESCRIPTION);
            double price = rs.getDouble(PostStruct.PRICE);
            String images = rs.getString(PostStruct.IMAGES);
            Timestamp createdTime = rs.getTimestamp(PostStruct.CREATE_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(PostStruct.UPDATED_AT);
            result.add(new Post(id, userId, title, description, images, price, createdTime, lastUpdatedTime));
        }
        return result;
    }
    
    public List<Post> getPostsByPage (int page, int pageSize) throws SQLException {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize + 1;
        return this.getPosts(from, to);
    }
    
    public void createPost (Post post) throws SQLException {
        String sql = "insert into Posts(user_id, title, description, images, price, created_at, updated_at) values(?,?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, post.getUserId());
        pre.setString(2, post.getTitle());
        pre.setString(3, post.getDescription());
        pre.setString(4, post.getImages());
        pre.setDouble(5, post.getPrice());
        long currentTimestamp = (new java.util.Date()).getTime();
        pre.setTimestamp(6, new Timestamp(currentTimestamp));
        pre.setTimestamp(7, new Timestamp(currentTimestamp));
        pre.execute();
    }
    
    
//    public boolean isExisted (String email) throws SQLException {
//        return (this.getUser(email) != null);
//    }
    
    public List<Post> getAllPost () throws SQLException {
        List<Post> result = new ArrayList<>();
        String sql = "select * from Posts";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt(PostStruct.ID);
            int userId = rs.getInt(PostStruct.USER_ID);
            String title = rs.getString(PostStruct.TITLE);
            String description = rs.getString(PostStruct.DESCRIPTION);
            double price = rs.getDouble(PostStruct.PRICE);
            String images = rs.getString(PostStruct.IMAGES);
            Timestamp createdTime = rs.getTimestamp(PostStruct.CREATE_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(PostStruct.UPDATED_AT);
            result.add(new Post(id, userId, title, description, images, price, createdTime, lastUpdatedTime));
        }
        return result;
    }
    
     public List<Post> getPostHistory (int userId) throws SQLException {
        List<Post> result = new ArrayList<>();
        String sql = "select * from Posts where user_id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, userId);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(PostStruct.ID);
            String title = rs.getString(PostStruct.TITLE);
            String description = rs.getString(PostStruct.DESCRIPTION);
            double price = rs.getDouble(PostStruct.PRICE);
            String images = rs.getString(PostStruct.IMAGES);
            Timestamp createdTime = rs.getTimestamp(PostStruct.CREATE_AT);
            Timestamp lastUpdatedTime = rs.getTimestamp(PostStruct.UPDATED_AT);
            result.add(new Post(id, userId, title, description, images, price, createdTime, lastUpdatedTime));
        }
        return result;
    }
    
    
    
    public static void main(String[] args) throws Exception {
        PostDAO dao = new PostDAO();
        try {
            List<Post> posts = dao.getPosts(2, 3);
            for(Post p:posts){
                System.out.println(p.getCreatedTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
