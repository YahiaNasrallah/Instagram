package com.example.roagram;

public class Post {

    private String username;
    private int photo;


    public Post(String username, int photo) {
        this.username = username;
        this.photo = photo;
    }

    public Post() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
