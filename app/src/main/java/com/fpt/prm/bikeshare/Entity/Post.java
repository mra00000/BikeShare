package com.fpt.prm.bikeshare.Entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private int id;
    private int userId;
    private String title;
    private String image;
    private String description;
    private int price;
    private Date created_at;
    private Date updated_at;

    public Post(int id, int userId, String title, String image, String description, int price, Date created_at, Date updated_at) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Post() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
