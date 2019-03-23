package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Adapter.ImageAdapter;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.StringHelper;
import com.fpt.prm.bikeshare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtDescription;
    private TextView txtTime;

    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTitle = (TextView) findViewById(R.id.txtDtitle);
        txtPrice = (TextView) findViewById(R.id.txtDprice);
        txtDescription = (TextView) findViewById(R.id.txtDdescription);
        txtTime = (TextView) findViewById(R.id.txtDTime);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("post");
        if(post != null){
            id = post.getId();
            Log.v("idPost","id: "+id);
            txtTitle.setText(post.getTitle());
            txtPrice.setText("Price: "+post.getPrice());
            txtDescription.setText(post.getDescription());
            txtTime.setText(post.getCreatedTime().toString());
            txtPrice.setTextColor(Color.BLUE);
            final List<String> imageUrls = StringHelper.toList(post.getImages(), "|");
//            new ProcessImage.GetImageFromURL(imageView).execute(imageUrls.get(0));

            ImageAdapter ia = new ImageAdapter(getBaseContext(), imageUrls);
            viewPager.setAdapter(ia);

        }
    }

}
