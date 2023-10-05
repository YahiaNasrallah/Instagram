package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.roagram.databinding.ActivityCreateOrLogBinding;

public class CreateOrLog extends AppCompatActivity {


    ActivityCreateOrLogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateOrLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Database mydatabase=new Database(this);

        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateAccount.class));
            }
        });



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.edEmail.getText().toString().equals("")||binding.edPassword.getText().toString().equals("")){
                    if (binding.edEmail.getText().toString().equals("")){
                        binding.edEmail.setError("Enter Data");
                        binding.edEmail.requestFocus();
                    }else {
                        binding.edPassword.setError("Enter Data");
                        binding.edPassword.requestFocus();
                    }

                }else {

                    if (mydatabase.CheckUser(binding.edEmail.getText().toString(),binding.edPassword.getText().toString())){
                        if (mydatabase.getTemp().equals("")){
                            mydatabase.AddTemp(binding.edEmail.getText().toString());
                        }else{
                            mydatabase.UpdateTemp(binding.edEmail.getText().toString());
                        }
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();




                    }else {

                        binding.edEmail.setError("Error in email");
                        binding.edEmail.requestFocus();
                        binding.edPassword.setError("Error in password");
                        binding.edPassword.requestFocus();
                    }


                }



            }
        });



        binding.edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edEmail.getText().toString().equals("")||binding.edPassword.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });



        binding.edPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edEmail.getText().toString().equals("")||binding.edPassword.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });





    }
}