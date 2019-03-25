package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.fpt.prm.bikeshare.Model.UserModel;
import com.fpt.prm.bikeshare.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
        implements HttpDataTransport.OnResponseListener {
    public static final int RC_SIGN_IN = 1000;
    public static final int RC_SIGN_UP = 1001;
    private TextView registerTxt;
    private SignInButton signInButton;
    private GoogleSignInClient signInClient;
    private UserModel userModel;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userModel = new UserModel();

        // Setting up sign in configurations
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.google_api_key))
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(this, gso);
        AppEnvironment.setGoogleSignInClient(signInClient);
        // Check if there has any signed user
        this.account = GoogleSignIn.getLastSignedInAccount(this);
        if (this.account != null) {
            this.userModel.findUserByGoogleToken(account.getIdToken(), LoginActivity.this);
        }
        this.signInButton = findViewById(R.id.sign_in_btn);
        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = signInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        this.registerTxt = findViewById(R.id.txt_register);
        this.registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppEnvironment.setCurrentUser(DataFaker.getFakeUser());
                Intent t = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            this.account = task.getResult(ApiException.class);
            if (requestCode == RC_SIGN_IN) {
                this.userModel.findUserByGoogleToken(this.account.getIdToken(), LoginActivity.this);
            }
        } catch (ApiException e) {
            AlertDialog alert =
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Connection error")
                            .setMessage(
                                    "Please check your internet connection and try again"
                            )
                            .setNegativeButton("OK", null)
                            .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject root = new JSONObject(response);
            int status = root.getInt("status");
            if (status == 0) {
                AlertDialog alert =
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Register")
                                .setMessage(
                                        "You haven't registered yet, do you want to register a new account ?"
                                )
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bundle signUpData = new Bundle();
                                        signUpData.putString("email", account.getEmail());
                                        signUpData.putString("name", account.getDisplayName());
                                        signUpData.putString("image", account.getPhotoUrl().toString());
                                        Intent t = new Intent(LoginActivity.this, RegisterActivity.class);
                                        t.putExtra("sign_up_data", signUpData);
                                        startActivity(t);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LoginActivity.this.signInClient.signOut();
                                    }
                                })
                                .show();
            } else if (status == -1) {
                AlertDialog alert =
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Account verification failed")
                                .setMessage(
                                        "We were unable to verify your account"
                                )
                                .setNegativeButton("OK", null)
                                .show();
            } else if (status == 1) {
                Gson gson = new Gson();
                String dataJson = root.getJSONObject("data").toString();
                User user = gson.fromJson(dataJson, User.class);
                user.setImage(this.account.getPhotoUrl().toString());
                AppEnvironment.setCurrentUser(user);
                Intent t = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(t);
            }
        } catch (JSONException e) {
            AlertDialog alert =
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Account verification failed")
                            .setMessage(
                                    "Error! data missmatch, please try again"
                            )
                            .setNegativeButton("OK", null)
                            .show();
        }
    }
}
