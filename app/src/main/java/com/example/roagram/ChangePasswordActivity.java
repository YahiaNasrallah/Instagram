package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.roagram.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {


    ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Database mydatabase=new Database(this);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.edOldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edOldPassword.getText().toString().equals("")||binding.edNewPassword.getText().toString().equals("")||binding.edNewPasswordRe.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));

                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });


        binding.edNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edOldPassword.getText().toString().equals("")||binding.edNewPassword.getText().toString().equals("")||binding.edNewPasswordRe.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));

                }
            }
        });


        binding.edNewPasswordRe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edOldPassword.getText().toString().equals("")||binding.edNewPassword.getText().toString().equals("")||binding.edNewPasswordRe.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mydatabase.getUserByEmail(mydatabase.getTemp()).getPassword().equals(binding.edOldPassword.getText().toString().trim())){

                    if (binding.edNewPassword.getText().toString().equals(binding.edNewPasswordRe.getText().toString())){

                        if (binding.edNewPasswordRe.getText().toString().length()>=6){
                            USER user=new USER(mydatabase.getUserByEmail(mydatabase.getTemp()).getUsername(),mydatabase.getUserByEmail(mydatabase.getTemp()).getName(),mydatabase.getUserByEmail(mydatabase.getTemp()).getBio(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail()
                                    ,binding.edNewPassword.getText().toString().trim(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());

                            if (mydatabase.UpdateUSer(user,mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail())) {
                                Toast.makeText(ChangePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else {
                            binding.edNewPassword.setError("Password must be at least 6 letters");
                            binding.edNewPassword.requestFocus();
                            binding.edNewPasswordRe.setError("Password must be at least 6 letters");
                            binding.edNewPasswordRe.requestFocus();
                        }


                    }else {
                        binding.edNewPassword.setError("Password not same");
                        binding.edNewPassword.requestFocus();
                        binding.edNewPasswordRe.setError("Password not same");
                        binding.edNewPasswordRe.requestFocus();
                    }


                }else {
                    binding.edOldPassword.setError("Enter Current Password");
                    binding.edOldPassword.requestFocus();
                }



            }
        });



    }
}