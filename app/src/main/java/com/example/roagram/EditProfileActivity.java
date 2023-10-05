package com.example.roagram;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.roagram.databinding.ActivityEditProfileBinding;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    ActivityEditProfileBinding binding;
    boolean flag=false;
    Bitmap image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Database mydatabase=new Database(this);
        binding.imageUser.setImageBitmap(mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());
        binding.edName.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getName());
        binding.edUsername.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getUsername());
        binding.edBio.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getBio());


        binding.imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.imageDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.imageDone.getVisibility()==View.VISIBLE){

                    USER user;

                    if (flag){
                        user=new USER(binding.edUsername.getText().toString().trim(),binding.edName.getText().toString(),binding.edBio.getText().toString(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail()
                                ,mydatabase.getUserByEmail(mydatabase.getTemp()).getPassword(),image2);

                    }else {
                        user=new USER(binding.edUsername.getText().toString().trim(),binding.edName.getText().toString(),binding.edBio.getText().toString(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num(),mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail()
                                ,mydatabase.getUserByEmail(mydatabase.getTemp()).getPassword(),mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());

                    }


                    if (mydatabase.UpdateUSer(user,mydatabase.getUserByEmail(mydatabase.getTemp()).getEmail())){
                        binding.imageDone.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.VISIBLE);



                        Thread timer= new Thread(() -> {
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finally
                            {
                                finish();
                            }
                        });
                        timer.start();

                    }
                }


            }
        });



        binding.edUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.imageDone.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);



                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        if (binding.edUsername.getText().toString().length() <= 4 || mydatabase.CheckUsername(binding.edUsername.getText().toString().trim())) {
                                binding.imageClose.setVisibility(View.VISIBLE);
                                binding.progressBar.setVisibility(View.GONE);
                                binding.imageDone.setVisibility(View.GONE);

                        }else {
                            binding.imageClose.setVisibility(View.GONE);
                            binding.progressBar.setVisibility(View.GONE);
                            binding.imageDone.setVisibility(View.VISIBLE);
                        }



                    }
                }.start();
            }

        });



        ActivityResultLauncher<Intent> lancher2=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent =result.getData();
                assert intent != null;
                Uri img=intent.getData();


                try {
                    image2 =  MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(),img);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                binding.imageUser.setImageBitmap(image2);
                flag=true;
            }
        }); {
        }


        //ينقلك للمعرض وبعدها يستخدم lancher2 ويعرض الصورة
        binding.textView9.setOnClickListener(new View.OnClickListener() {
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