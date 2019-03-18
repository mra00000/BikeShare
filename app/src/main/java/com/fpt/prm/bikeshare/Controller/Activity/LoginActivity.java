package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN = 1000;
    public static final int RC_SIGN_UP = 1001;
    private TextView registerTxt;
    private SignInButton signInButton;
    private GoogleSignInClient signInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Setting up sign in configurations
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(this, gso);
        // Check if there has any signed user
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            // Todo: query user data from server's database

            User user = DataFaker.getFakeUser();
            AppEnvironment.setCurrentUser(user);
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
                Intent signInIntent = signInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_UP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (requestCode == RC_SIGN_IN) {
                // Todo: query user data from server's database

                User user = DataFaker.getFakeUser();
                AppEnvironment.setCurrentUser(user);
            } else if (requestCode == RC_SIGN_UP) {
                Bundle signUpData = new Bundle();
                signUpData.putString("email", account.getEmail());
                signUpData.putString("name", account.getDisplayName());
                signUpData.putString("image", account.getPhotoUrl().toString());
                Intent t = new Intent(this, RegisterActivity.class);
                t.putExtra("sign_up_data", signUpData);
                startActivity(t);
            }
        } catch (ApiException e) {
            Log.v("sign in info: ", "sign in failed");
            e.printStackTrace();
        }
    }
}
