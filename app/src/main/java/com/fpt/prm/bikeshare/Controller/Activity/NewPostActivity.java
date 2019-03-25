package com.fpt.prm.bikeshare.Controller.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fpt.prm.bikeshare.Common.Constanst;
import com.fpt.prm.bikeshare.Common.ProcessImage;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.PostRequest;
import com.fpt.prm.bikeshare.Helper.StringHelper;
import com.fpt.prm.bikeshare.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewPostActivity extends AppCompatActivity {
    private EditText txtTitle;
    private EditText txtDescription;
    private EditText txtPrice;
    private Button btnSend;
    private Button btnPick;
    private Button btnRemove;
    private TextView txtImageCount;
    RequestQueue rq;
    private ArrayList filePaths = new ArrayList<String>();
    private static final int  REQUEST_GET_SINGLE_FILE = 1;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        this.txtTitle = findViewById(R.id.txtTitlePost);
        this.txtDescription = findViewById(R.id.txtDescriptionPost);
        this.txtPrice = findViewById(R.id.txtPricePost);
        this.btnSend = findViewById(R.id.btnSend);
        this.btnPick = findViewById(R.id.btnPickImage);
        this.btnRemove = findViewById(R.id.btnRemoveImage);
        this.txtImageCount = findViewById(R.id.txtPickedCount);
        rq = Volley.newRequestQueue(this);
        this.btnPick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                Uri data = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
                intent.setDataAndType(data,"image/*");
                startActivityForResult(intent,REQUEST_GET_SINGLE_FILE);
            }
        });

        this.btnRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                filePaths.clear();
                txtImageCount.setText("You must pick at least one image");
                Toast.makeText( NewPostActivity.this, "Removed all image", Toast.LENGTH_SHORT).show();
            }
        });
        this.btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = txtTitle.getText().toString();
                String description = txtDescription.getText().toString();
                String price = txtPrice.getText().toString();
                String images = StringHelper.join("|", filePaths);

                if(title.equals("") || description.equals("") || price.equals("") || images.equals("")){
                    Toast.makeText( NewPostActivity.this, "You must fill all blank", Toast.LENGTH_SHORT).show();
                    if ( images.equals("")) txtImageCount.setTextColor(Color.RED);
                    return;
                }

                if(Double.parseDouble(price)<100){
                    Toast.makeText( NewPostActivity.this, "Minimum price is $100", Toast.LENGTH_SHORT).show();
                    txtPrice.setText("100");
                }else {
                StringRequest MyStringRequest = null;
                try {
                    txtImageCount.setTextColor(Color.GRAY);
                    btnSend.setText("Posting...");
                    btnSend.setClickable(false);
                    createPostRequest(title, description, price, images);

                } catch (IOException e) {
                    e.printStackTrace();
                }}
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_GET_SINGLE_FILE) {
                    Uri selectedImageUri = data.getData();
                       String path = getRealPathFromURI(this, selectedImageUri);

                    String strBase64 = new ProcessImage().ConvertImage2Base64(path);
                    this.filePaths.add(strBase64);
                    txtImageCount.setText("You picked "+filePaths.size()+" image");
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            Log.e("Faild", "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GET_SINGLE_FILE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, REQUEST_GET_SINGLE_FILE);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }
    public  void createPostRequest(final String title, final String des, final String price, final String image) throws IOException {
        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/createPost";
        //rq = Volley.newRequestQueue(context);

        StringRequest myStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Post post = gson.fromJson(response, Post.class);
                int postId = post.getId();
                Intent intent = new Intent(NewPostActivity.this, DetailActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
                finish();
//                try {
//                    getPostByIdRequest(postId);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                User user = AppEnvironment.getCurrentUser();
                String id = String.valueOf(user.getId());
                String token = AppEnvironment.getGoogleSignInAccount().getIdToken();
                MyData.put("userId", id);
                MyData.put("title", title);
                MyData.put("token", token);
                MyData.put("description", des);
                MyData.put("price", price);
                MyData.put("images", image);
                return MyData;
            }
        };

        rq.add(myStringRequest);
    }
    public void getPostByIdRequest(final int postId) throws IOException {
        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/postInfo?postId="+postId;
        //RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Gson gson = new Gson();
//                Post post = gson.fromJson(response, Post.class);
//                Intent intent = new Intent(NewPostActivity.this, DetailActivity.class);
//                intent.putExtra("post", post);
//                startActivity(intent);
//                finish();
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.e("errorResponse","err1");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                return MyData;
            }
        };
        rq.add(myStringRequest);

    }
}
