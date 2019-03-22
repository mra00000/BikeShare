package com.fpt.prm.bikeshare.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fpt.prm.bikeshare.Adapter.ListPostAdapter;
import com.fpt.prm.bikeshare.Controller.Activity.NewPostActivity;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.Helper.PostRequest;
import com.fpt.prm.bikeshare.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    private ListView listPostView;
    private Button btnNewPost;
    User user;
    List<Post>  listPost;

//    public void updateList(List<Post> listPost) {
//        this.listPost = listPost;
//        this.updateListPost();
//    }

    public void updateListPost () {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listPost = new ArrayList<>();
        this.user = AppEnvironment.getCurrentUser();



    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
//        this.view = v;
        this.btnNewPost = view.findViewById(R.id.btnNewPost);
        this.btnNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getContext(), NewPostActivity.class);
                startActivity(t);
            }
        });

        listPostView = view.findViewById(R.id.listPostsView);
        ListPostAdapter adapter = new ListPostAdapter(this.getActivity(), R.layout.post_layout, listPost);
        listPostView.setAdapter(adapter);
        try {
            PostRequest.getPostRequest(getActivity().getApplicationContext(), listPost, adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }


}
