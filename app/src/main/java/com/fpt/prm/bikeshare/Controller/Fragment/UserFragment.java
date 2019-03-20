package com.fpt.prm.bikeshare.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Controller.Activity.BalanceManageActivity;
import com.fpt.prm.bikeshare.Entity.User;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.R;

public class UserFragment extends Fragment {
    private TextView txtUserName;
    private TextView txtEmail;
    private TextView txtPhone;
    private TextView txtAddress;
    private TextView txtBalance;
    private Button manageBalance;
    private ImageView userImage;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = AppEnvironment.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        this.txtUserName = v.findViewById(R.id.txt_user_name);
        this.txtEmail = v.findViewById(R.id.txt_email);
        this.txtPhone = v.findViewById(R.id.txt_phone_number);
        this.txtAddress = v.findViewById(R.id.txt_address);
        this.txtBalance = v.findViewById(R.id.txt_balance);
        this.manageBalance = v.findViewById(R.id.btn_balance_manage);
        this.userImage = v.findViewById(R.id.user_image);

        this.txtUserName.setText(this.user.getName());
        this.txtEmail.setText(this.user.getEmail());
        this.txtPhone.setText(this.user.getPhone());
        this.txtAddress.setText(this.user.getAddress());
        this.txtBalance.setText("$" + this.user.getBalance());

        this.manageBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getContext(), BalanceManageActivity.class);
                startActivity(t);
            }
        });
        return v;
    }
}
