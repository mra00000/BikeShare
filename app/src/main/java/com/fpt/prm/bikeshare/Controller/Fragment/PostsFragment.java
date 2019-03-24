package com.fpt.prm.bikeshare.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class PostsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView listPostView;
    private Button btnNewPost;
    ListPostAdapter adapter;
    User user;
    List<Post>  listPost;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.listPost = new ArrayList<>();
        this.user = AppEnvironment.getCurrentUser();

        super.onCreate(savedInstanceState);

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
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        listPostView = view.findViewById(R.id.listPostsView);

        adapter = new ListPostAdapter(this.getActivity(), R.layout.post_layout, listPost);
        getData();

        listPostView.setAdapter(adapter);
        return view;
    }
    public void getData(){
        try {
            PostRequest.getPostRequest(getActivity().getApplicationContext(), listPost, adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRefresh() {
        adapter = (ListPostAdapter) listPostView.getAdapter();
        getData();
        listPostView.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        }, 2000);
    }
}
