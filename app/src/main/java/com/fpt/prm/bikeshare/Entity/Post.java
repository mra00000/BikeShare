package com.fpt.prm.bikeshare.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
public class Post implements Serializable {


    private int id;
    private int userId;
    private String title;
    private String description;
    private String images;
    private double price;
    private Timestamp createdTime, lastUpdatedTime;
    public Post() {
    }

    public Post(int id, int userId, String title, String description, String images, double price, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.images = images;
        this.price = price;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Post(int userId, String title, String description, String images, double price, Timestamp createdTime, Timestamp lastUpdatedTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.images = images;
        this.price = price;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Post(int userId, String title, String description, String images, double price) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.images = images;
        this.price = price;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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