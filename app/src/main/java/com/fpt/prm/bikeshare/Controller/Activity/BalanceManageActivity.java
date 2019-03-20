package com.fpt.prm.bikeshare.Controller.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fpt.prm.bikeshare.Adapter.BasicTabViewAdapter;
import com.fpt.prm.bikeshare.Controller.Fragment.BalanceChargeFragment;
import com.fpt.prm.bikeshare.Controller.Fragment.BalanceWithdrawFragment;
import com.fpt.prm.bikeshare.R;

public class BalanceManageActivity extends AppCompatActivity {
    private final String[] TAB_TITLES =
            new String[]{"Charge", "Withdraw"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_manage);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.balance_view_pager);

        BasicTabViewAdapter adapter = this.getViewPagerAdapter();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        this.setTabsIcon(tabLayout, adapter.getCount());
    }

    private BasicTabViewAdapter getViewPagerAdapter() {
        BasicTabViewAdapter adapter =
                new BasicTabViewAdapter(getSupportFragmentManager());
        adapter.addItem(new BalanceChargeFragment());
        adapter.addItem(new BalanceWithdrawFragment());
        return adapter;
    }

    private void setTabsIcon(TabLayout tabLayout, int count) {
        for (int i = 0; i < count; i++) {
            tabLayout.getTabAt(i).setText(this.TAB_TITLES[i]);
        }
    }
}
