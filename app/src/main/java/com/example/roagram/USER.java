package com.example.roagram;

import android.graphics.Bitmap;

public class USER {


    private String username;
    private String name;
    private String bio;
    private int posts_num;
    private int follwrs_num;
    private int follwing_num;
    private String email;
    private String password;
    private Bitmap Photo;


    public USER(){

    }


    public USER(String username, String email, String password, Bitmap photo) {
        this.username = username;
        this.email = email;
        this.password = password;
        Photo = photo;
        name=username;
        bio="";
        posts_num=0;
        follwing_num=0;
        follwrs_num=0;
    }

    public USER(String username, String name, String bio, int posts_num, int follwrs_num, int follwing_num, String email, String password, Bitmap photo) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.posts_num = posts_num;
        this.follwrs_num = follwrs_num;
        this.follwing_num = follwing_num;
        this.email = email;
        this.password = password;
        Photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getPhoto() {
        return Photo;
    }

    public void setPhoto(Bitmap photo) {
        Photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPosts_num() {
        return posts_num;
    }

    public void setPosts_num(int posts_num) {
        this.posts_num = posts_num;
    }

    public int getFollwrs_num() {
        return follwrs_num;
    }

    public void setFollwrs_num(int follwrs_num) {
        this.follwrs_num = follwrs_num;
    }

    public int getFollwing_num() {
        return follwing_num;
    }

    public void setFollwing_num(int follwing_num) {
        this.follwing_num = follwing_num;
    }
}
