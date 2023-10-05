package com.example.roagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.roagram.databinding.FragmentProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    BottomSheetDialog bottomSheetDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater, container, false);
        preferences= getContext().getSharedPreferences("MyPrefe", Context.MODE_PRIVATE);
        editor=preferences.edit();
        Database mydatabase=new Database(getContext());

        bottomSheetDialog=new BottomSheetDialog(getContext());
        creatDialog();




        binding.imageUser.setImageBitmap(mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());
        binding.tvPostNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num()));
        binding.tvFollwersNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num()));
        binding.tvFollwingNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num()));
        binding.tvUserBio.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getBio());
        binding.tvUsernamereal.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getUsername());
        binding.tvUserName.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getName());



        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });


        binding.swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.imageUser.setImageBitmap(mydatabase.getUserByEmail(mydatabase.getTemp()).getPhoto());
                binding.tvPostNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getPosts_num()));
                binding.tvFollwersNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwrs_num()));
                binding.tvFollwingNum.setText(String.valueOf(mydatabase.getUserByEmail(mydatabase.getTemp()).getFollwing_num()));
                binding.tvUserBio.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getBio());
                binding.tvUsernamereal.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getUsername());
                binding.tvUserName.setText(mydatabase.getUserByEmail(mydatabase.getTemp()).getName());

                binding.swiper.setRefreshing(false);

            }
        });



        binding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        bottomSheetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);



        return binding.getRoot();
    }

    private void creatDialog() {

        View view=getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);

        LinearLayout layout_password=view.findViewById(R.id.lay_password);
        LinearLayout layout_email=view.findViewById(R.id.lay_email);
        LinearLayout layout_out=view.findViewById(R.id.lay_out);


        layout_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ChangePasswordActivity.class));
                bottomSheetDialog.hide();

            }
        });
        layout_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ChangeEmail.class));
                bottomSheetDialog.hide();
            }
        });
        layout_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getContext(),CreateOrLog.class);
                startActivity(intent);
                bottomSheetDialog.hide();
            }
        });

        bottomSheetDialog.setContentView(view);

    }
}