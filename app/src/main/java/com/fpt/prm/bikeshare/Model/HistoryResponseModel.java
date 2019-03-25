package com.fpt.prm.bikeshare.Model;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;

import java.util.HashMap;
import java.util.Map;

public class HistoryResponseModel {
    private int userId, postId;
    private String action, phone;
    public HistoryResponseModel(int userId, int postId, String action, String phone) {
        this.userId = userId;
        this.postId = postId;
        this.action = action;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
