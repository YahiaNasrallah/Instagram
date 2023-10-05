package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.roagram.databinding.ActivityShowAccountBinding;

public class ShowAccount extends AppCompatActivity {

    ActivityShowAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String email=intent.getStringExtra("index");
        Database mydatabase=new Database(this);


        binding.imageUser.setImageBitmap(mydatabase.getUserByEmail(email).getPhoto());
        binding.tvPostNum.setText(String.valueOf(mydatabase.getUserByEmail(email).getPosts_num()));
        binding.tvFollwersNum.setText(String.valueOf(mydatabase.getUserByEmail(email).getFollwrs_num()));
        binding.tvFollwingNum.setText(String.valueOf(mydatabase.getUserByEmail(email).getFollwing_num()));
        binding.tvUserBio.setText(mydatabase.getUserByEmail(email).getBio());
        binding.tvUsernamereal.setText(mydatabase.getUserByEmail(email).getUsername());
        binding.tvUserName.setText(mydatabase.getUserByEmail(email).getName());




    }
}