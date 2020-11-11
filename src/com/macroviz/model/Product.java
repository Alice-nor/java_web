package com.macroviz.model;

public class Product {
    protected int id;
    protected String picture;
    protected String name;
    protected String price;
    protected String description;
    protected String created_at;
    protected String updated_at;
    public Product(int id, String picture, String name, String price, String description, String created_at, String updated_at) {
        super();
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Product(String picture, String name, String price, String description, String created_at, String updated_at) {
        super();
        this.picture = picture;
        this.name = name;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
