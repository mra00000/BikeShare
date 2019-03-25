package com.fpt.prm.bikeshare.Model;

import com.fpt.prm.bikeshare.Helper.HttpDataTransport;

import java.util.HashMap;
import java.util.Map;

public class TransactionModel {
    public static final String HOST = "http://10.0.3.2:8080/BikeShare/";

    public void chargeBalance(String email, String cardNumber, String cardSerial, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_PUT);
        transport.setUrl(HOST + "api/transactions");
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("card_number", cardNumber);
        data.put("card_serial", cardSerial);
        transport.setData(data);
        transport.setOnResponseListener(listener);
        transport.execute();
    }

    public void withdrawBalance(String token, String amount, HttpDataTransport.OnResponseListener listener) {
        HttpDataTransport transport = new HttpDataTransport();
        transport.setMethod(HttpDataTransport.HTTP_POST);
        transport.setUrl(HOST + "api/transactions");
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        data.put("amount", String.valueOf(amount));
        transport.setData(data);
        transport.setOnResponseListener(listener);
        transport.execute();
    }

}
