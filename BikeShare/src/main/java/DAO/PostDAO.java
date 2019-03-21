/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Post;
import Model.User;
import Services.FirebaseHelper;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

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
    
    public int createPost (Post post) throws SQLException {
        String sql = "insert into Posts(user_id, title, description, images, price, created_at, updated_at) values(?,?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pre.setInt(1, post.getUserId());
        pre.setString(2, post.getTitle());
        pre.setString(3, post.getDescription());
        pre.setString(4, post.getImages());
        pre.setDouble(5, post.getPrice());
        long currentTimestamp = (new java.util.Date()).getTime();
        pre.setTimestamp(6, new Timestamp(currentTimestamp));
        pre.setTimestamp(7, new Timestamp(currentTimestamp));
        int isAdded = pre.executeUpdate();
        System.out.println(isAdded);
        if (isAdded > 0) {
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
            else return -1;
        } else return -1;
    }
    
    public boolean updatePost(Post post) throws SQLException {
        String sql = "update posts "
                + "set user_id = ?, title = ?, images = ?, description = ?, price = ?, created_at = ?, updated_at = ? "
                + "where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, post.getUserId());
        pre.setString(2, post.getTitle());
        pre.setString(3, post.getImages());
        pre.setString(4, post.getDescription());
        pre.setDouble(5, post.getPrice());
        pre.setTimestamp(6, post.getCreatedTime());
        long currentTime = (new java.util.Date()).getTime();
        pre.setTimestamp(7, new Timestamp(currentTime));
        pre.setInt(8, post.getId());
        int ok = pre.executeUpdate();
        return ok > 0;
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
     
     public String uploadImage(String base64Image, String fileName) {
        try {
            byte[] imageByte;
            BufferedImage bufferedImage = null;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64Image);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(bis);
            String url = FirebaseHelper.upload(imageByte, fileName);
            return url;
        } catch (Exception e){
            return "";
        }
     }
    
    
    
    public static void main(String[] args) throws Exception {
        PostDAO dao = new PostDAO();
        try {
            long time = (new Date()).getTime();
            Post post = new Post(3, 2, "cho muon xe xxx", "minh cho muon xe thu 7 va chu nhat tuan nay......", "https://storage.googleapis.com/bikeshare-fb429.appspot.com/test5.png?GoogleAccessId=firebase-adminsdk-0g30i@bikeshare-fb429.iam.gserviceaccount.com&Expires=1584715482&Signature=OYfYEjHMoiraEtMJSyBM5hkUl4l2fNxTUqyLZOmUo2E2fsDlBbFQdi%2BSiRNRiHecwVGB5mZTA9fVio02c1e0rS6AlgIWqsOa5n2UplJnxbcFC6h0rVB4lx7w6zBEbOyUYuAYCUcaelDl3Dr0pmxTrllUjbkjERrPZKWcl8omwkiLhTQOvhvQW4L4kjLgYDpfDYHT2qZ73J8h5L7mwO1nynYrMD1g2t%2FNMrbLxdt2NVGkE21Kro9P8pvlgjfESThj8ljNuJ%2BDqey8RNGIhcs0TCFOs%2FVQcfqV6RuLD6DRq3ALlX0jwVPDXRvtgNfDC2OtDXCTjol2jh58rRpTRyRtFA%3D%3D",
                12.123, new Timestamp(time), new Timestamp(time));
            dao.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
