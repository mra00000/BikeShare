package com.fpt.prm.bikeshare.Controller.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fpt.prm.bikeshare.Adapter.ListHistoryAdapter;
import com.fpt.prm.bikeshare.Adapter.ListPostAdapter;
import com.fpt.prm.bikeshare.Entity.History;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.R;

import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView listHistoryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listHistoryView = view.findViewById(R.id.listHistory);
        List<History> list =  DataFaker.getFakeListHistory();
        ListHistoryAdapter lha = new ListHistoryAdapter( this.getActivity(), R.layout.history_layout, list);
        listHistoryView.setAdapter(lha);

        return view;
    }

}
