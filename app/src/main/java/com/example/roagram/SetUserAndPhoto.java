package com.example.roagram;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.roagram.databinding.ActivitySetUserAndPhotoBinding;

import java.io.IOException;

public class SetUserAndPhoto extends AppCompatActivity {

    Bitmap image2;
    ActivitySetUserAndPhotoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetUserAndPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Database database=new Database(this);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edPassword.getText().toString().length()<=4||database.CheckUsername(binding.edPassword.getText().toString())){

                    if (database.CheckUsername(binding.edPassword.getText().toString())){
                        binding.edPassword.setError("username was taken");
                        binding.edPassword.requestFocus();
                    }else {
                        binding.edPassword.setError("must be at least 5 letters at least");
                        binding.edPassword.requestFocus();
                    }

                }else {



                    Intent intent2=getIntent();
                    String email=intent2.getStringExtra("email");
                    String password=intent2.getStringExtra("password");

                    USER user=new USER(binding.edPassword.getText().toString().trim(),email,password,image2);

                    if (database.AddUser(user)) {

                        Toast.makeText(SetUserAndPhoto.this, "Account Created!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), CreateOrLog.class);

                        startActivity(intent);
                        finish();

                    }



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


                if (binding.edPassword.getText().toString().equals("")){


                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue3));

                }else {
                    binding.btnLogin.setBackgroundTintList(getResources().getColorStateList(R.color.blue));

                }

                if (binding.edPassword.getText().toString().length()<=4&&database.CheckUsername(binding.edPassword.getText().toString())){
                    if (binding.edPassword.getText().toString().length()<=4){
                        binding.edPassword.setError("must be at least 5 letters at least");
                        binding.edPassword.requestFocus();
                    }else {
                        binding.edPassword.setError("username was taken");
                        binding.edPassword.requestFocus();
                    }

                }



            }
        });

        ActivityResultLauncher<Intent> lancher2=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent =result.getData();
                assert intent != null;
                Uri img=intent.getData();


                try {
                    image2 =  MediaStore.Images.Media.getBitmap(SetUserAndPhoto.this.getContentResolver(),img);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                binding.choosePhoto.setImageBitmap(image2);
            }
        }); {
        }


        //ينقلك للمعرض وبعدها يستخدم lancher2 ويعرض الصورة
        binding.choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                lancher2.launch(intent2);
            }
        });






    }
}