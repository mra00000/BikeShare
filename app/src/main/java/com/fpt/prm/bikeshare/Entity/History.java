package com.fpt.prm.bikeshare.Entity;

import java.sql.Timestamp;
import java.util.Date;

public class History {

    private int id;
    private int userId;
    private int postId;
    private String action;
    private Timestamp createdTime, lastUpdatedTime;
    public History() {
    }

    public History(int id, int userId, int postId, String action, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public History(int userId, int postId, String action, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public History(int userId, int postId, String action) {
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