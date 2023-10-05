package com.example.roagram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.roagram.databinding.ActivityCreateAccountBinding;

public class CreateAccount extends AppCompatActivity {


    ActivityCreateAccountBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//                binding.btnPhone.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
//                binding.btnPhone.setTextColor(getResources().getColor(R.color.white));









        binding.edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {





            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edEmail.getText().toString().equals("")){



                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));


                }else {

                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));


                }
            }
        });



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edEmail.getText().toString().endsWith("@gmail.com")){
                    Intent intent=new Intent(getApplicationContext(), SetPassword.class);
                    intent.putExtra("email",binding.edEmail.getText().toString().trim());
                    startActivity(intent);
                    finish();
                }else {
                    if (binding.edEmail.getText().toString().equals("")){
                        binding.edEmail.setError("Enter Data");
                        binding.edEmail.requestFocus();
                    }
                    if (!binding.edEmail.getText().toString().endsWith("@gmail.com")){
                        binding.edEmail.setError("Enter invalid Email");
                        binding.edEmail.requestFocus();
                    }
                }                }




        });


        binding.imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateOrLog.class));
                finish();
            }
        });



    }
}