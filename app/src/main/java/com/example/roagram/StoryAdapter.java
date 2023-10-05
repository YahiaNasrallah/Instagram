package com.example.roagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roagram.databinding.StoryItemBinding;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Story> storyList;

    
    //استدعاء الinterface 
    private ClickHandle clickHandle;

    StoryItemBinding binding;


    public StoryAdapter(Context context, List<Story> storyList, ClickHandle clickHandle) {
        this.context = context;
        this.storyList = storyList;
        this.clickHandle=clickHandle;
    }


    //الدالتين لاعطاء الاوامر للعناصر
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=StoryItemBinding.inflate(LayoutInflater.from(context),parent,false);
            return new MyviewHolder(binding);
    }

    //الوصول لكل عنصر محدد من الليست
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MyviewHolder myviewHolder= (MyviewHolder) holder;

        myviewHolder.binding.userPhoto.setImageResource(storyList.get(position).getImage());
        myviewHolder.binding.usernameItem.setText(storyList.get(position).getUsername());


        myviewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickHandle.onItemClick(position);

            }
        });




    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }


    //ربط التصميم
    public class MyviewHolder extends RecyclerView.ViewHolder{

        StoryItemBinding binding;
        public MyviewHolder(StoryItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



    //اصدار امر عند الضغط على عنصر ولكن من main
    public interface ClickHandle{
        void onItemClick(int position);

    }



}
