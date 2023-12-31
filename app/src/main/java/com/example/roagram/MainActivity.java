package com.example.roagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.roagram.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences= this.getSharedPreferences("MyPrefe", this.MODE_PRIVATE);
        editor=preferences.edit();
        replaceFragment(new HomeFragment());

        binding.bottom.setOnItemSelectedListener(item ->{

            if (item.getItemId()==R.id.menu_home){
                replaceFragment(new HomeFragment());
            }else if (item.getItemId()==R.id.menu_search){
                replaceFragment(new SearchFragment());
            }else if (item.getItemId()==R.id.menu_new){
                startActivity(new Intent(getApplicationContext(), UplodeImageForPostActivity.class));
            }else if (item.getItemId()==R.id.menu_reel){

            }else if (item.getItemId()==R.id.menu_profile){
                replaceFragment(new ProfileFragment());
            }


            return true;
        });



    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}