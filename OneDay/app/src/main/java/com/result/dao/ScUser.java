package com.result.dao;


public class ScUser {
    private int id;
    private String data;
    private String title;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public ScUser(int id, String data, String title, String image) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.image = image;
    }

    public ScUser() {
        super();
    }

    @Override
    public String toString() {
        return "ScUser{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
