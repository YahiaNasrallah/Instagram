package com.example.roagram;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roagram.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    SearchAdapter searchAdapter;

    FragmentSearchBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentSearchBinding.inflate(inflater, container, false);

        Database database=new Database(getContext());

        binding.divider.setVisibility(View.INVISIBLE);
        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (binding.edSearch.getText().toString().isEmpty()){
                    binding.divider.setVisibility(View.INVISIBLE);

                    searchAdapter =new SearchAdapter(getContext(), database.SearchAllUsersByName(" "), new SearchAdapter.ClickHandle() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent=new Intent(getContext(), ShowAccount.class);
                            intent.putExtra("index",database.SearchAllUsersByName(binding.edSearch.getText().toString()).get(position).getEmail());
                            startActivity(intent);
                        }
                    });

                    binding.recycle.setAdapter(searchAdapter);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.recycle.setLayoutManager(layoutManager);

                }
                else {
                    binding.divider.setVisibility(View.VISIBLE);


                    searchAdapter =new SearchAdapter(getContext(), database.SearchAllUsersByName(binding.edSearch.getText().toString()), new SearchAdapter.ClickHandle() {
                        @Override
                        public void onItemClick(int position) {


                            Intent intent=new Intent(getContext(), ShowAccount.class);
                            intent.putExtra("index",database.SearchAllUsersByName(binding.edSearch.getText().toString()).get(position).getEmail());
                            startActivity(intent);


                        }
                    });

                    binding.recycle.setAdapter(searchAdapter);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.recycle.setLayoutManager(layoutManager);



                }

            }







            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edSearch.getText().toString().isEmpty()){
                    binding.divider.setVisibility(View.INVISIBLE);

                    searchAdapter =new SearchAdapter(getContext(), database.SearchAllUsersByName(" "), new SearchAdapter.ClickHandle() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent=new Intent(getContext(), ShowAccount.class);
                            intent.putExtra("index",database.SearchAllUsersByName(binding.edSearch.getText().toString()).get(position).getEmail());
                            startActivity(intent);
                        }
                    });

                    binding.recycle.setAdapter(searchAdapter);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.recycle.setLayoutManager(layoutManager);




                }
                else {
                    binding.divider.setVisibility(View.VISIBLE);

                    searchAdapter =new SearchAdapter(getContext(), database.SearchAllUsersByName(binding.edSearch.getText().toString()), new SearchAdapter.ClickHandle() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent=new Intent(getContext(), ShowAccount.class);
                            intent.putExtra("index",database.SearchAllUsersByName(binding.edSearch.getText().toString()).get(position).getEmail());
                            startActivity(intent);
                        }
                    });

                    binding.recycle.setAdapter(searchAdapter);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.recycle.setLayoutManager(layoutManager);


                }
            }
        });







        return binding.getRoot();
    }
}