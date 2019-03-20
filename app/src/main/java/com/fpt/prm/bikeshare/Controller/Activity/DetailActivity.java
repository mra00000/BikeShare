package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Adapter.ListPostAdapter;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.R;

public class DetailActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtDescription;
    private ImageView imageView;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTitle = (TextView) findViewById(R.id.txtDtitle);
        txtPrice = (TextView) findViewById(R.id.txtDprice);
        txtDescription = (TextView) findViewById(R.id.txtDdescription);
        imageView = findViewById(R.id.imageView2);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("post");
        if(post != null){
            id = post.getId();
            Log.v("idPost","id: "+id);
            txtTitle.setText(post.getTitle());
            txtPrice.setText("Price: "+post.getPrice());
            txtDescription.setText(post.getDescription());
            txtTitle.setTextColor(Color.RED);
            txtPrice.setTextColor(Color.BLUE);

            new ListPostAdapter.GetImageFromURL(imageView).execute(post.getImage());
        }
    }

    public void clickBack(View view) {
        Intent intent = new Intent();
        setResult(200, intent);
        finish();
    }
}
