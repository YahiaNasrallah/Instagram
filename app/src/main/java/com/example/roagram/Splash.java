package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.roagram.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {


    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        Thread timer= new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally
            {
                Intent i=new Intent(Splash.this,CreateOrLog.class);
                startActivity(i);
                finish();
            }
        });
        timer.start();



    }
}