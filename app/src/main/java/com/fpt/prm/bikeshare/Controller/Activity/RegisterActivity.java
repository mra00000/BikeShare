package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.fpt.prm.bikeshare.Model.UserModel;
import com.fpt.prm.bikeshare.R;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity
                implements View.OnClickListener, HttpDataTransport.OnResponseListener {
    private ImageView userImage;
    private TextView welcomeTxt;
    private EditText userPhone;
    private EditText userAddress;
    private Button btnRegisyer;
    private UserModel userModel;
    private Bundle signUpData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Todo: load user image from url

        this.welcomeTxt = findViewById(R.id.txt_welcome);
        this.userPhone = findViewById(R.id.edit_sign_up_phone);
        this.userAddress = findViewById(R.id.edit_sign_up_address);
        this.btnRegisyer = findViewById(R.id.btn_register);
        this.btnRegisyer.setOnClickListener(this);


        Intent t = this.getIntent();
        this.signUpData = t.getBundleExtra("sign_up_data");

        this.welcomeTxt.setText(String.format(getResources().getString(R.string.register_username), signUpData.getString("name")));

        // Todo: validate user input
        // Todo: Check email address on server
    }

    @Override
    public void onClick(View v) {
        this.userModel = new UserModel();

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
        this.userModel.registerUser(newUser, this);
    }

    @Override
    public void onResponse(String response) {
        if (response != null && response.equals("0")) {
            AlertDialog alert =
                    new AlertDialog.Builder(this)
                            .setTitle("You're in")
                            .setMessage(
                                    "Registration complete"
                            )
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent t = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(t);
                                }
                            })
                            .show();
        } else {
            AlertDialog alert =
                    new AlertDialog.Builder(this)
                            .setTitle("Registration failed")
                            .setMessage(
                                    "Sorry, an error has occurred, please try again"
                            )
                            .setNegativeButton("OK", null)
                            .show();
        }
    }
}
