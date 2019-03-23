package com.fpt.prm.bikeshare.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Entity.History;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.R;

import java.util.List;

public class ListHistoryAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<History> list;

    public ListHistoryAdapter(Activity activity, int layout, List<History> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
        }
        History history = list.get(position);
        TextView txtTime = view.findViewById(R.id.txtHistoryTime);
        TextView txtAction = view.findViewById(R.id.txtAction);
        TextView txtPrice = view.findViewById(R.id.txtHistoryPrice);
        txtTime.setText(history.getCreatedTime().toString());
        txtAction.setText(history.getAction());
//        txtPrice.setText(history.getPostId());


        return view;
    }
}
