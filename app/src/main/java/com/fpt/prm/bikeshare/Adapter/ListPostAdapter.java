package com.fpt.prm.bikeshare.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Common.ProcessImage;
import com.fpt.prm.bikeshare.Controller.Activity.DetailActivity;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.R;

import java.io.InputStream;
import java.net.URL;
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
        final Post p = list.get(position);

        txtTitle.setText(p.getTitle());
        txtTitle.setTextColor(Color.RED);
        txtPrice.setText("Price: "+String.valueOf(p.getPrice()));
        txtPrice.setTextColor(Color.BLUE);
        Button btnDetail = view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your code here
                Intent intent = new Intent(activity.getApplicationContext(), DetailActivity.class);
                intent.putExtra("post",p);
                activity.startActivityForResult(intent, 100);


            }
        });
        List<String> image = p.getImage();
        imgView = view.findViewById(R.id.imageView);

//        new GetImageFromURL(img).execute(image.get(0));
        new ProcessImage.GetImageFromURL(imgView).execute(image.get(0));
        return view;

    }


}
