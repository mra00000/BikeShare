package com.fpt.prm.bikeshare.Controller.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fpt.prm.bikeshare.R;

public class BalanceChargeFragment extends Fragment {
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
        return v;
    }
}