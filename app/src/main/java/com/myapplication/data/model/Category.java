package com.myapplication.data.model;

public class Category {
    private String category_id, category, user_id;

    public Category(String category, String user_id)
    {
        this.category = category;
        this.user_id = user_id;
    }

    public Category(String category)
    {
        this.category = category;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Category(String category_id, String category, String user_id) {
        this.category_id = category_id;
        this.category = category;
        this.user_id = user_id;
    }

    public Category() {

    }
}
