package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.roagram.databinding.ActivityChangeEmailBinding;

public class ChangeEmail extends AppCompatActivity {


    ActivityChangeEmailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangeEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Database mydatabase=new Database(this);


        binding.edCurrentEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edCurrentEmail.getText().toString().equals("")||binding.edCurrentEmail.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });
        binding.edNewEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edCurrentEmail.getText().toString().equals("")||binding.edCurrentEmail.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.edNewEmail.getText().toString().isEmpty()||binding.edCurrentEmail.getText().toString().isEmpty()){
                    if (binding.edNewEmail.getText().toString().isEmpty()){
                        binding.edNewEmail.setError("Enter Data");
                        binding.edNewEmail.requestFocus();
                    }else {
                        binding.edCurrentEmail.setError("Enter Data");
                        binding.edCurrentEmail.requestFocus();
                    }
                }else {
                    if (mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail().equals(binding.edCurrentEmail.getText().toString().trim())){

                        if (binding.edNewEmail.getText().toString().endsWith("@gmail.com")){
                            USER user=new USER(mydatabase.getUserByEmail(mydatabase.getTemp()).getUsername(),mydatabase.getUserByEmail(mydatabase.getTemp()).getName(),mydatabase.getUserByEmail(mydatabase.getTemp()).getBio(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num(),binding.edNewEmail.getText().toString()
                                    ,mydatabase.getUserByEmail(mydatabase.getTemp()).getPassword(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());

                            if (mydatabase.UpdateUSer(user,mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail())) {
                                Toast.makeText(ChangeEmail.this, "Email Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else {
                            binding.edNewEmail.setError("Enter invalid Email");
                            binding.edNewEmail.requestFocus();
                        }


                    }else {
                        binding.edCurrentEmail.setError("Enter Current Email");
                        binding.edCurrentEmail.requestFocus();
                    }
                }



            }
        });







    }
}