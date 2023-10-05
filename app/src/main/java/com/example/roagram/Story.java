package com.example.roagram;

public class Story {


    int image;
    String username;


    public Story(){

    }

    public Story(String username,int image) {
        this.image = image;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
