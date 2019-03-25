package com.fpt.prm.bikeshare.Controller.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fpt.prm.bikeshare.Adapter.ListHistoryAdapter;
import com.fpt.prm.bikeshare.Adapter.ListPostAdapter;
import com.fpt.prm.bikeshare.Controller.Activity.MainActivity;
import com.fpt.prm.bikeshare.Entity.History;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.Helper.HistoryRequest;
import com.fpt.prm.bikeshare.Model.HistoryResponseModel;
import com.fpt.prm.bikeshare.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView listHistoryView;
    List<HistoryResponseModel> listHistory;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listHistory = new ArrayList<>();
        this.user = AppEnvironment.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listHistoryView = view.findViewById(R.id.listHistory);
        ListHistoryAdapter adapter = new ListHistoryAdapter( this.getActivity(), R.layout.history_layout, listHistory);
        listHistoryView.setAdapter(adapter);

        try {
            String token = AppEnvironment.getGoogleSignInAccount().getIdToken();
            HistoryRequest.getHistoryRequest(getContext(), token, user.getId(), listHistory, adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listHistoryView.setAdapter(adapter);
        return view;
    }

}
