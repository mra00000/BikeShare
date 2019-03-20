package com.fpt.prm.bikeshare.Controller.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fpt.prm.bikeshare.Adapter.ListPostAdapter;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.R;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    private ListView listPostView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_posts, container, false);
        listPostView = v.findViewById(R.id.listPostsView);
        List<Post>  list =  DataFaker.getFakeListPost();
        ListPostAdapter lpa = new ListPostAdapter( this.getActivity(), R.layout.post_layout, list);
        listPostView.setAdapter(lpa);
        return v;
    }


}
