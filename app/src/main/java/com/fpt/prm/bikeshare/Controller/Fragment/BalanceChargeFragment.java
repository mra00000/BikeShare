package com.fpt.prm.bikeshare.Controller.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fpt.prm.bikeshare.Controller.Activity.MainActivity;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.fpt.prm.bikeshare.Model.TransactionModel;
import com.fpt.prm.bikeshare.R;

public class BalanceChargeFragment extends Fragment
                implements HttpDataTransport.OnResponseListener, DialogInterface.OnClickListener {
    private EditText cardNumber;
    private EditText cardSerial;
    private Button chargeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_balance_charge, container, false);
        this.cardNumber = v.findViewById(R.id.charge_card_number);
        this.cardSerial = v.findViewById(R.id.charge_card_serial);
        this.chargeBtn = v.findViewById(R.id.btn_charge);

        this.chargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardSerialValue = BalanceChargeFragment.this.cardSerial.getText().toString();
                String cardNumberValue = BalanceChargeFragment.this.cardNumber.getText().toString();
                // Todo: Input validation

                User currentUser = AppEnvironment.getCurrentUser();
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.chargeBalance(currentUser.getEmail(), cardNumberValue, cardSerialValue, BalanceChargeFragment.this);
            }
        });
        return v;
    }

    @Override
    public void onResponse(String response) {
        if (response != null && !response.equals("-1")) {
            double balance = Double.parseDouble(response);
            AppEnvironment.getCurrentUser().setBalance(balance);
            new AlertDialog.Builder(getContext())
                    .setTitle("Charging successful")
                    .setMessage(
                            "Your balance is now " + response
                    )
                    .setNegativeButton("OK", this)
                    .show();
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("Connection error")
                    .setMessage(
                            "Sorry, something went wrong"
                    )
                    .setNegativeButton("OK", this)
                    .show();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent t = new Intent(getContext(), MainActivity.class);
        startActivity(t);
    }
}