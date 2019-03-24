package com.fpt.prm.bikeshare.Helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fpt.prm.bikeshare.Common.Constanst;
import com.fpt.prm.bikeshare.Controller.Activity.DetailActivity;
import com.fpt.prm.bikeshare.Controller.Fragment.PostsFragment;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Entity.User;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRequest {

    public static void createPostRequest(final Context context, final String title, final String des, final String price, final String image) throws IOException {
        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/createPost";
        RequestQueue rq = Volley.newRequestQueue(context);

        StringRequest myStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsestatus", "ok");
                Gson gson = new Gson();
                Post post = gson.fromJson(response, Post.class);
                int postId = post.getId();

                Post p = new Post();
                try {
                    getPostByIdRequest(context, postId, p);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
//                User user = AppEnvironment.getCurrentUser();
//                user.getId();

                MyData.put("userId", "3");
                MyData.put("title", title);
                MyData.put("token", "123");
                MyData.put("description", des);
                MyData.put("price", price);
                MyData.put("images", image);
                return MyData;
            }
        };

        rq.add(myStringRequest);
    }


//MyRequestQueue.add(MyStringRequest);
public static void getPostRequest(Context context, final List<Post> list, final BaseAdapter adapter) throws IOException {
    String url = "http://"+ Constanst.ipHost +"/BikeShare/api/posts?page=1&pageSize=100";
    RequestQueue rq = Volley.newRequestQueue(context);
    StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("responsexxx", response);
            Gson gson = new Gson();
            List<Post> posts = new ArrayList(Arrays.asList(gson.fromJson(response, Post[].class)));
            for(Post post : posts){
                list.add(post);
            };
            adapter.notifyDataSetChanged();
            Log.d("responsestatus", "ok");
            //This code is executed if the server responds, whether or not the response contains data.
            //The String 'response' contains the server's response.
        }
    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
        @Override
        public void onErrorResponse(VolleyError error) {
            //This code is executed if there is an error.
        }
    }) {
        protected Map<String, String> getParams() {
            Map<String, String> MyData = new HashMap<String, String>();
//                User user = AppEnvironment.getCurrentUser();
//                user.getId();

//            MyData.put("page", "1");
//            MyData.put("pageSize", "1000");

            return MyData;
        }

    };

    rq.add(myStringRequest);

}
    public static void getPostByIdRequest(final Context context, final int postId, Post post) throws IOException {
        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/postInfo?postId="+postId;
        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsexxx", response);
                Gson gson = new Gson();
                Post post = gson.fromJson(response, Post.class);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("post", post);
                context.startActivity(intent);
                Log.d("responsestatus", "ok");
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

//                MyData.put("postId", postId);

                return MyData;
            }

        };

        rq.add(myStringRequest);

    }


}
