package com.example.roagram;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.roagram.databinding.ActivityUplodeImageForPostBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UplodeImageForPostActivity extends AppCompatActivity {

    Bitmap image2;
    ActivityUplodeImageForPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUplodeImageForPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        String currentTime = sdf.format(new Date());
        String currentDate = sdf2.format(new Date());

        binding.tvTime.setText(currentDate +" - "+currentTime);

        binding.imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ActivityResultLauncher<Intent> lancher2=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent =result.getData();
                assert intent != null;
                Uri img=intent.getData();


                try {
                    image2 =  MediaStore.Images.Media.getBitmap(UplodeImageForPostActivity.this.getContentResolver(),img);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                binding.image.setImageBitmap(image2);
            }
        }); {
        }


        //ينقلك للمعرض وبعدها يستخدم lancher2 ويعرض الصورة
        binding.image.setOnClickListener(new View.OnClickListener() {
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