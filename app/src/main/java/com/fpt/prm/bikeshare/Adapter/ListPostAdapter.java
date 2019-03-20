package com.fpt.prm.bikeshare.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Controller.Activity.DetailActivity;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListPostAdapter extends BaseAdapter {
    private ImageView img;
    private static Bitmap bitmap;
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
        TextView txtTitle = view.findViewById(R.id.txtTitle);
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

        img = view.findViewById(R.id.imageView);
        Log.d("image:", p.getImage());
        new GetImageFromURL(img).execute(p.getImage());

        return view;

    }

    public static class GetImageFromURL extends AsyncTask<String, Void, Bitmap>{
       ImageView imageView;

       public GetImageFromURL(ImageView imageView){
           this.imageView = imageView;
       }
        @Override
        protected Bitmap doInBackground(String... url) {
           String urlImage = url[0];
           bitmap = null;
           try{
               InputStream srt = new URL(urlImage).openStream();
               bitmap = BitmapFactory.decodeStream(srt);
           }catch (Exception e){
               e.printStackTrace();
           }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
