package com.fpt.prm.bikeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private ImageView userImage;
    private TextView welcomeTxt;
    private EditText userPhone;
    private EditText userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Todo: load user image from url

        this.welcomeTxt = findViewById(R.id.txt_welcome);
        this.userPhone = findViewById(R.id.edit_sign_up_phone);
        this.userAddress = findViewById(R.id.edit_sign_up_address);


        Intent t = this.getIntent();
        Bundle signUpData = t.getBundleExtra("sign_up_data");

        this.welcomeTxt.setText(String.format(getResources().getString(R.string.register_username), signUpData.getString("name")));

        // Todo: validate user input
        // Todo: Check email address on server

        User newUser = new User(
            -1,
                signUpData.getString("name"),
                "",
                signUpData.getString("email"),
                userPhone.getText().toString(),
                signUpData.getString("image"),
                0,
                new Date(),
                new Date()
        );

        // Todo: save data to server's database here

        AppEnvironment.setCurrentUser(newUser);
    }
}
