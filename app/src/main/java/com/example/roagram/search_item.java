package com.example.roagram;

import android.graphics.Bitmap;

public class search_item {

    private Bitmap image;
    private String username;
    private String name;
    private String follwers;

    public search_item(){

    }

    public search_item(Bitmap image, String username, String name, String follwers) {
        this.image = image;
        this.username = username;
        this.name = name;
        this.follwers = follwers;
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollwers() {
        return follwers;
    }

    public void setFollwers(String follwers) {
        this.follwers = follwers;
    }
}
