package com.fpt.prm.bikeshare.Helper;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpDataTransport extends AsyncTask<Void, Void, String> {
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";

    private String url;
    private String method;
    private Map<String, String> data;
    private OnResponseListener listener;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void setOnResponseListener(OnResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(this.method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            List<String> requestParams = new ArrayList<>();
            for(Map.Entry<String, String> entry : this.data.entrySet()) {
                requestParams.add(entry.getKey() + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            String requestUri = TextUtils.join("&", requestParams);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestUri);
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String temp;
            while ((temp = reader.readLine()) != null) {
                result += temp;
            }
            reader.close();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        this.listener.onResponse(s);
    }

    public interface OnResponseListener {
        void onResponse(String response);
    }
}
