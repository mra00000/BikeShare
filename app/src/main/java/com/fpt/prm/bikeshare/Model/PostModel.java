package com.fpt.prm.bikeshare.Model;

import com.fpt.prm.bikeshare.Helper.HttpDataTransport;

import java.util.HashMap;
import java.util.Map;

public class PostModel {
    public static final String HOST = "http://34.80.20.194:8080/BikeShare/";

    public PostModel() {}

    public void findPostByPostId(String postId, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_GET);
        transport.setUrl(HOST + "api/postInfo");
        Map<String, String> requestData = new HashMap<>();
        requestData.put("postId", postId);
        transport.setData(requestData);
        transport.setOnResponseListener(listener);
        transport.execute();
    }


}
