/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author ahcl
 */
public class Transaction {
    private int id, userId;
    private String action;
    private double amount;
    private Timestamp createdTime;

    public Transaction() {
    }

    public Transaction(int id, int userId, String action, double amount, Timestamp createdTime) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.amount = amount;
        this.createdTime = createdTime;
    }
    
    public Transaction(int userId, String action, double amount, Timestamp createdTime) {
        this.userId = userId;
        this.action = action;
        this.amount = amount;
        this.createdTime = createdTime;
    }

    public Transaction(int userId, String action, double amount) {
        this.userId = userId;
        this.action = action;
        this.amount = amount;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
    
    
    
}
