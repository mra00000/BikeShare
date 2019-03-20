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
public class Booking {
    private int id;
    private int userId;
    private int postId;
    private String action;
    private Timestamp createdTime, lastUpdatedTime;
    public Booking() {
    }

    public Booking(int id, int userId, int postId, String action, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Booking(int userId, int postId, String action, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
    public Booking(int userId, int postId, String action) {
        this.userId = userId;
        this.postId = postId;
        this.action = action;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
