package com.fpt.prm.bikeshare.Controller.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fpt.prm.bikeshare.Controller.Activity.MainActivity;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.HttpDataTransport;
import com.fpt.prm.bikeshare.Model.TransactionModel;
import com.fpt.prm.bikeshare.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.squareup.picasso.Picasso;

public class BalanceWithdrawFragment extends Fragment
        implements HttpDataTransport.OnResponseListener, DialogInterface.OnClickListener{
    private EditText editAmount;
    private Button btnWithdraw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_balance_withdraw, container, false);
        this.editAmount = v.findViewById(R.id.withdraw_amount);
        this.btnWithdraw = v.findViewById(R.id.btn_withdraw);

        this.btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = GoogleSignIn.getLastSignedInAccount(getContext()).getIdToken();
                String amount = BalanceWithdrawFragment.this.editAmount.getText().toString();
                // Todo: input validation
                TransactionModel model = new TransactionModel();
                model.withdrawBalance(token, amount, BalanceWithdrawFragment.this);
            }
        });
        return v;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent t = new Intent(getContext(), MainActivity.class);
        startActivity(t);
    }

    @Override
    public void onResponse(String response) {
        if (response != null) {
            if (response.equals("-1")) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Invalid amount")
                        .setMessage(
                                "Sorry, this amount is not enough for withdrawal"
                        )
                        .setNegativeButton("OK", this)
                        .show();
            } else if (response.equals("-2")) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Connection error")
                        .setMessage(
                                "Sorry, something went wrong"
                        )
                        .setNegativeButton("OK", this)
                        .show();
            } else {
                double balance = Double.parseDouble(response);
                AppEnvironment.getCurrentUser().setBalance(balance);
                new AlertDialog.Builder(getContext())
                        .setTitle("Withdraw successful")
                        .setMessage(
                                "The amount of money has been sent to your momo with your phone number"
                        )
                        .setNegativeButton("OK", this)
                        .show();
            }
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
}
