package com.fpt.prm.bikeshare.Controller.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.fpt.prm.bikeshare.R;

public class NewPostActivity extends AppCompatActivity {
    private EditText txtTitle;
    private EditText txtDescription;
    private EditText txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        this.txtTitle = findViewById(R.id.txtTitlePost);
        this.txtDescription = findViewById(R.id.txtDescriptionPost);
        this.txtPrice = findViewById(R.id.txtPricePost);

    }
}
