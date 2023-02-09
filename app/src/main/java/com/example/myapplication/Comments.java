package com.example.myapplication;

public class Comments {
    private int id, news_id;
    private String name, text;

    public Comments(){}

    public Comments(int id, int news_id, String name, String text) {
        this.id = id;
        this.news_id = news_id;
        this.name = name;
        this.text = text;
    }
    public  Comments(int news_id, String name, String text) {
            this.news_id = news_id;
            this.name = name;
            this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
