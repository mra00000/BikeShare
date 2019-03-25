package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Adapter.ImageAdapter;
import com.fpt.prm.bikeshare.Controller.Fragment.HistoryFragment;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.fpt.prm.bikeshare.Helper.StringHelper;
import com.fpt.prm.bikeshare.Model.RentModel;
import com.fpt.prm.bikeshare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements HttpDataTransport.OnResponseListener, DialogInterface.OnClickListener{
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtDescription;
    private TextView txtTime;
    private Button btnRent;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTitle = (TextView) findViewById(R.id.txtDtitle);
        txtPrice = (TextView) findViewById(R.id.txtDprice);
        txtDescription = (TextView) findViewById(R.id.txtDdescription);
        txtTime = (TextView) findViewById(R.id.txtDTime);
        btnRent = findViewById(R.id.btnRent);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final Intent intent = getIntent();
        final Post post = (Post) intent.getSerializableExtra("post");
        final User user = AppEnvironment.getCurrentUser();

        if(post != null){
            id = post.getId();
            Log.v("idPost","id: "+id);
            txtTitle.setText(post.getTitle());
            txtPrice.setText("Price: $"+post.getPrice());
            txtDescription.setText(post.getDescription());
            txtTime.setText(post.getCreatedTime().toString());
            txtPrice.setTextColor(Color.BLUE);
            final List<String> imageUrls = StringHelper.toList(post.getImages(), "|");
            ImageAdapter ia = new ImageAdapter(getBaseContext(), imageUrls);
            viewPager.setAdapter(ia);
        }
        btnRent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //check balance
                if(post.getPrice() > user.getBalance()){
                    new AlertDialog.Builder(DetailActivity.this)
                            .setTitle("Error something went wrong")
                            .setMessage(
                                    "Sorry, your balance is not enought! Please try againt with other post."
                            )
//                            .setNegativeButton("OK", this)
                            .show();
                    return;
                }
                //TODO send request rent to server
                String token = String.valueOf(AppEnvironment.getGoogleSignInAccount().getIdToken());
                String userId = String.valueOf(AppEnvironment.getCurrentUser().getId());
                String postUserId = String.valueOf(post.getUserId());
                if(userId.equals(postUserId)){
                    new AlertDialog.Builder(DetailActivity.this)
                            .setTitle("Error")
                            .setMessage(
                                    "Sorry, you can not rent your bike by yourself. Please choose other bike."
                            )
//                            .setNegativeButton("OK", this)
                            .show();
                    return;
                }
                RentModel rentModel = new RentModel();
                rentModel.sendRentRequest(token, userId, String.valueOf(post.getId()), DetailActivity.this);


            }
        });
    }

    @Override
    public void onResponse(String response) {
        //TODO change to activity history
//            Intent intent = new Intent(DetailActivity.this, MainActivity.this);
//                MyFragment fragment = new MyFragment();
//                Fragment fragment = HistoryFragment.
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.home_view_pager, DetailActivity.this.getClass().newInstance());
//                transaction.commit();

        //TODO update balance
        if (response != null && !response.equals("-1")) {
            Log.d("res","msg");
            double balance = Double.parseDouble(response);
            AppEnvironment.getCurrentUser().setBalance(balance);
            new AlertDialog.Builder(this)
                    .setTitle("Rent successful")
                    .setMessage(
                            "Please contact to owner bike by Phone in history. Your balance is now " + response
                    )
                    .setNegativeButton("OK", this)
                    .show();
            Intent t = new Intent(this, MainActivity.class);
            startActivity(t);
        } else {
            Log.e("response", response);
            new AlertDialog.Builder(this)
                    .setTitle("Connection error")
                    .setMessage(
                            response
                    )
                    .setNegativeButton("OK", this)
                    .show();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
