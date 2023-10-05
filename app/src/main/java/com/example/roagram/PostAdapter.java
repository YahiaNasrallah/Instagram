package com.example.roagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roagram.databinding.PostItemBinding;
import com.example.roagram.databinding.StoryItemBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Post> postList;


    //استدعاء الinterface
    private ClickHandle clickHandle;

    PostItemBinding binding;


    public PostAdapter(Context context, List<Post> postList, ClickHandle clickHandle) {
        this.context = context;
        this.postList = postList;
        this.clickHandle=clickHandle;
    }


    //الدالتين لاعطاء الاوامر للعناصر
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=PostItemBinding.inflate(LayoutInflater.from(context),parent,false);
            return new MyviewHolder(binding);
    }

    //الوصول لكل عنصر محدد من الليست
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MyviewHolder myviewHolder= (MyviewHolder) holder;

        myviewHolder.binding.imagePost.setImageResource(postList.get(position).getPhoto());
        myviewHolder.binding.tvUsernamePost.setText(postList.get(position).getUsername());


        myviewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickHandle.onItemClick(position);

            }
        });




    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    //ربط التصميم
    public class MyviewHolder extends RecyclerView.ViewHolder{

        PostItemBinding binding;
        public MyviewHolder(PostItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }



    //اصدار امر عند الضغط على عنصر ولكن من main
    public interface ClickHandle{
        void onItemClick(int position);

    }



}
