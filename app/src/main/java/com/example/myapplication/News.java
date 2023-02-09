package com.example.myapplication;

public class News {

    private int id;
    private String title, text, categoryName, imagePath, date;

    public News(){}
    public News(int id, String title, String text, String categoryName, String imagePath, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.categoryName = categoryName;
        this.imagePath = imagePath;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
