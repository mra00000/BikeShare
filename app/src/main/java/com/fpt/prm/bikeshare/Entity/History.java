package com.fpt.prm.bikeshare.Entity;

import java.util.Date;

public class History {
    private int id;
    private int userId;
    private String action;
    private int postId;
    private Date created_at;
    private Date updated_at;

    public History(int id, int userId, String action, int postId, Date created_at, Date updated_at) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.postId = postId;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
