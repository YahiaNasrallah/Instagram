package com.example.roagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roagram.databinding.AccountSearchItemBinding;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<USER> searchItemList;

    
    //استدعاء الinterface 
    private ClickHandle clickHandle;

    AccountSearchItemBinding binding;


    public SearchAdapter(Context context, List<USER> searchItemList, ClickHandle clickHandle) {
        this.context = context;
        this.searchItemList = searchItemList;
        this.clickHandle=clickHandle;
    }


    //الدالتين لاعطاء الاوامر للعناصر
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=AccountSearchItemBinding.inflate(LayoutInflater.from(context),parent,false);
            return new MyviewHolder(binding);
    }

    //الوصول لكل عنصر محدد من الليست
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {

        MyviewHolder myviewHolder= (MyviewHolder) holder;

        myviewHolder.binding.tvUsernameItem.setText(searchItemList.get(position).getUsername());
        myviewHolder.binding.tvNameItem.setText(searchItemList.get(position).getName());
        myviewHolder.binding.iamgeUserItem.setImageBitmap(searchItemList.get(position).getPhoto());


        myviewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickHandle.onItemClick(position);

            }
        });




    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
    }


    //ربط التصميم
    public class MyviewHolder extends RecyclerView.ViewHolder{

        AccountSearchItemBinding binding;
        public MyviewHolder(AccountSearchItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



    //اصدار امر عند الضغط على عنصر ولكن من main
    public interface ClickHandle{
        void onItemClick(int position);

    }



}
