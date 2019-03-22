package com.fpt.prm.bikeshare.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Controller.Activity.DetailActivity;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.StringHelper;
import com.fpt.prm.bikeshare.Helper.TimeHelper;
import com.fpt.prm.bikeshare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPostAdapter extends BaseAdapter {
    private ImageView imgView;
    private Activity activity;
    private int layout;
    private List<Post> list;

    public ListPostAdapter(Activity activity, int layout, List<Post> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    public ListPostAdapter() {
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
        TextView txtTitle = view.findViewById(R.id.txtTitlePost);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtTime = view.findViewById(R.id.txtTime);

        final Post p = list.get(position);

        txtTitle.setText(p.getTitle());
        txtTitle.setTextColor(Color.RED);
        txtPrice.setText("Price: "+String.valueOf(p.getPrice()));
        txtPrice.setTextColor(Color.BLUE);
//        Button btnDetail = view.findViewById(R.id.btnDetail);
//        txtTime.setText(TimeHelper.getTime(p.getCreatedTime()));
                txtTime.setText(String.valueOf(p.getCreatedTime()));

        RelativeLayout relativeLayout = view.findViewById(R.id.relativeClickable);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your code here
                Intent intent = new Intent(activity.getApplicationContext(), DetailActivity.class);
                intent.putExtra("post",p);
                activity.startActivityForResult(intent, 100);


            }
        });
//        btnDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Your code here
//                Intent intent = new Intent(activity.getApplicationContext(), DetailActivity.class);
//                intent.putExtra("post",p);
//                activity.startActivityForResult(intent, 100);
//
//
//            }
//        });
        String image = p.getImages();
        List<String> images = StringHelper.toList(image, "|");
        imgView = view.findViewById(R.id.imageView);

//        new GetImageFromURL(img).execute(image.get(0));
        if(images.size()>0) {
//            images.remove(0);
            Picasso.get().load(images.get(0)).into(imgView);
        }
        return view;

    }


}
