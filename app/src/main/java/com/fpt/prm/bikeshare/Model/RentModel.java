package com.fpt.prm.bikeshare.Model;

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
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentModel {
    public static final String HOST = "http://34.80.20.194:8080/BikeShare/";

    public void sendRentRequest(String token, String userId, String postId, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_POST);
        transport.setUrl(HOST + "api/createBooking");
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        data.put("userId", userId);
        data.put("postId", postId);
        transport.setData(data);
        transport.setOnResponseListener(listener);
        transport.execute();
    }
}