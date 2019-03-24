package com.fpt.prm.bikeshare.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fpt.prm.bikeshare.Common.Constanst;
import com.fpt.prm.bikeshare.Entity.History;
import com.fpt.prm.bikeshare.Entity.Post;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryRequest {
    public static void getHistoryRequest(Context context, final int userId, final List<History> list, final BaseAdapter adapter) throws IOException {
        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/bookingHistory";
        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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

                MyData.put("token", "1");
                //TODO replace userId sample
//              MyData.put("userId", String.valueOf(userId));
                MyData.put("userId", "3");
                return MyData;
            }

        };

        rq.add(myStringRequest);

    }
}
