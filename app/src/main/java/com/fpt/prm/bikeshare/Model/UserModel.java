package com.fpt.prm.bikeshare.Model;

import android.util.Log;

import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    public static final String HOST = "http://34.80.20.194:8080/BikeShare/";

    public UserModel() {}

    public void findUserByGoogleToken(String token, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_POST);
        transport.setUrl(HOST + "api/users");
        Map<String, String> requestData = new HashMap<>();
        requestData.put("token", token);
        transport.setData(requestData);
        transport.setOnResponseListener(listener);
        transport.execute();
    }

    public void registerUser(User newUser, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_PUT);
        transport.setUrl(HOST + "api/users");
        Map<String, String> requestData = new HashMap<>();
        requestData.put("name", newUser.getName());
        requestData.put("email", newUser.getEmail());
        requestData.put("image", newUser.getImage());
        requestData.put("phone", newUser.getPhone());
        transport.setData(requestData);
        transport.setOnResponseListener(listener);
        transport.execute();
    }
}
