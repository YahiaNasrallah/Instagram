package com.example.roagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roagram.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    StoryAdapter storyAdapter;
    PostAdapter postAdapter;
    private List<Story> storyList;
    private List<Post> postList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater, container, false);

        storyList=new ArrayList<Story>();

        storyList.add(new Story("User 1",R.drawable.gaza));
        storyList.add(new Story("User 2",R.drawable.gaza));
        storyList.add(new Story("User 3",R.drawable.gaza));
        storyList.add(new Story("User 4",R.drawable.gaza));
        storyList.add(new Story("User 5",R.drawable.gaza));
        storyList.add(new Story("User 6",R.drawable.gaza));
        storyList.add(new Story("User 7",R.drawable.gaza));
        storyList.add(new Story("User 8",R.drawable.gaza));
        storyList.add(new Story("User 9",R.drawable.gaza));
        storyList.add(new Story("User 10",R.drawable.gaza));
        storyList.add(new Story("User 11",R.drawable.gaza));


        storyAdapter =new StoryAdapter(getContext(), storyList, new StoryAdapter.ClickHandle() {
            @Override
            public void onItemClick(int position) {
            }


        });

        binding.recycleStory.setAdapter(storyAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
        //GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        binding.recycleStory.setLayoutManager(layoutManager);





        postList=new ArrayList<Post>();

        postList.add(new Post("User 1",R.drawable.gaza));
        postList.add(new Post("User 2",R.drawable.gaza));
        postList.add(new Post("User 3",R.drawable.gaza));
        postList.add(new Post("User 4",R.drawable.gaza));





        postAdapter =new PostAdapter(getContext(), postList, new PostAdapter.ClickHandle() {
            @Override
            public void onItemClick(int position) {

            }
        });

        binding.recyclePost.setAdapter(postAdapter);

        LinearLayoutManager layoutManager2=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        //GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        binding.recyclePost.setLayoutManager(layoutManager2);



        return binding.getRoot();
    }
}