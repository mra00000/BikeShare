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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRequest {




//MyRequestQueue.add(MyStringRequest);
    public static void getPostRequest(Context context, final List<Post> list, final BaseAdapter adapter) throws IOException {
    String url = "http://"+ Constanst.ipHost +"/BikeShare/api/posts?page=1&pageSize=10";
    RequestQueue rq = Volley.newRequestQueue(context);
    StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("responsexxx", response);
            Gson gson = new Gson();
            List<Post> posts = new ArrayList(Arrays.asList(gson.fromJson(response, Post[].class)));
            list.clear();
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
            return MyData;
        }
    };
    rq.add(myStringRequest);
    }
}
