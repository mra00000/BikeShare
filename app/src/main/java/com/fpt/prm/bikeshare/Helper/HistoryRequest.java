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
import com.fpt.prm.bikeshare.Model.HistoryResponseModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryRequest {
    private List<History> histories;

    public HistoryRequest(List<History> histories) {
        this.histories = histories;
    }

    public List<History> getHistories() {
        return histories;
    }

    public static void getHistoryRequest(Context context, final String token, final int userId, final List<HistoryResponseModel> list, final BaseAdapter adapter) throws IOException {

        String url = "http://"+ Constanst.ipHost +"/BikeShare/api/bookingHistory";
        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                List<HistoryResponseModel> histories = new ArrayList(Arrays.asList(gson.fromJson(response, HistoryResponseModel[].class)));
                list.clear();
                for(HistoryResponseModel history : histories){
                    list.add(history);
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
                MyData.put("token", token);
                MyData.put("userId", String.valueOf(userId));
                return MyData;
            }
        };
        rq.add(myStringRequest);
    }
}
