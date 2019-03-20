/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ahcl
 */
public class User {
    private int id;
    private String name, phone, email, password;
    private double balance;
    private Timestamp createdTime, lastUpdatedTime;
    public User() {
        
    }

    public User(int id, String name, String phone, String email, String password, double balance, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
    public User(String name, String phone, String email, String password, double balance, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }  
    
    public User(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
}
