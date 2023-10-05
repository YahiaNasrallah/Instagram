package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.example.roagram.databinding.ActivitySetPasswordBinding;

public class SetPassword extends AppCompatActivity {

    ActivitySetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent2=getIntent();
        String email=intent2.getStringExtra("email");

        binding.edPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edPassword.getText().toString().equals("")||binding.edPasswordre.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });


        binding.edPasswordre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {





            }

            @Override
            public void afterTextChanged(Editable s) {


                if (binding.edPassword.getText().toString().equals("")||binding.edPasswordre.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });



        binding.imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateAccount.class));
                finish();
            }
        });




        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edPassword.getText().toString().equals(binding.edPasswordre.getText().toString())&&!binding.edPassword.getText().toString().equals("")&&!binding.edPasswordre.getText().toString().equals("")&&binding.edPassword.getText().toString().length()>=6){

                    Intent intent=new Intent(getApplicationContext(), SetUserAndPhoto.class);
                    intent.putExtra("password",binding.edPassword.getText().toString().trim());
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                }else {

                    if (binding.edPassword.getText().toString().length()>=6){
                        if (binding.edPassword.getText().toString().equals("")||binding.edPasswordre.getText().toString().equals("")){
                            if (binding.edPassword.getText().toString().equals("")){
                                binding.edPassword.setError("Enter Data");
                                binding.edPassword.requestFocus();
                            }else {
                                binding.edPasswordre.setError("Enter Data");
                                binding.edPasswordre.requestFocus();
                            }

                        }




                        if (!binding.edPassword.getText().toString().equals(binding.edPasswordre.getText().toString())&&!binding.edPassword.getText().toString().equals("")&&!binding.edPassword.getText().toString().equals("")){
                            binding.edPasswordre.setError("Password not same");
                            binding.edPasswordre.requestFocus();
                            binding.edPassword.setError("Password not same");
                            binding.edPassword.requestFocus();
                        }
                    }else {
                        binding.edPassword.setError("Password must be at least 6 letters");
                        binding.edPassword.requestFocus();
                    }




                }                }




        });





    }
}