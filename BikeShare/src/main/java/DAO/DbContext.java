/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ahcl
 */
public class DbContext {
    public Connection getConnection() throws Exception {
        String user = "SA";
        String password = "laVita@123";
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName("34.80.20.194");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("BikeShare");
        return dataSource.getConnection();
    }
    public static void main(String[] args) {
        DbContext db = new DbContext();
        try {
            db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
