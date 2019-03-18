package com.fpt.prm.bikeshare.Controller.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.fpt.prm.bikeshare.Adapter.HomeTabViewPagerAdapter;
import com.fpt.prm.bikeshare.Controller.Fragment.HistoryFragment;
import com.fpt.prm.bikeshare.Controller.Fragment.PosstFragment;
import com.fpt.prm.bikeshare.Controller.Fragment.UserFragment;
import com.fpt.prm.bikeshare.R;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tabLayout = findViewById(R.id.tab_layout);
        this.viewPager = findViewById(R.id.home_view_pager);

        HomeTabViewPagerAdapter adapter = this.getViewPagerAdapter();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        this.setTabsIcon(tabLayout, adapter.getCount());
    }

    private HomeTabViewPagerAdapter getViewPagerAdapter() {
        HomeTabViewPagerAdapter adapter =
                new HomeTabViewPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new PosstFragment());
        adapter.addItem(new HistoryFragment());
        adapter.addItem(new UserFragment());
        return adapter;
    }

    private void setTabsIcon(TabLayout tabLayout, int count) {
        for (int i = 0; i < count; i++) {
            tabLayout.getTabAt(i).setIcon(R.mipmap.ic_launcher_round);
        }
    }
}
