package com.fpt.prm.bikeshare.Controller.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.fpt.prm.bikeshare.Adapter.BasicTabViewAdapter;
import com.fpt.prm.bikeshare.Controller.Fragment.HistoryFragment;
import com.fpt.prm.bikeshare.Controller.Fragment.PostsFragment;
import com.fpt.prm.bikeshare.Controller.Fragment.UserFragment;
import com.fpt.prm.bikeshare.Helper.AppEnvironment;
import com.fpt.prm.bikeshare.Helper.DataFaker;
import com.fpt.prm.bikeshare.R;

public class MainActivity extends AppCompatActivity {
    private final String[] TAB_TITLES =
            new String[]{"Home", "History", "User"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppEnvironment.setCurrentUser(DataFaker.getFakeUser());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.home_view_pager);

        BasicTabViewAdapter adapter = this.getViewPagerAdapter();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        this.setTabsIcon(tabLayout, adapter.getCount());
    }


    private HomeTabViewPagerAdapter getViewPagerAdapter() {
        HomeTabViewPagerAdapter adapter =
                new HomeTabViewPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new PostsFragment());

        adapter.addItem(new HistoryFragment());
        adapter.addItem(new UserFragment());
        return adapter;
    }

    private void setTabsIcon(TabLayout tabLayout, int count) {
        for (int i = 0; i < count; i++) {
            tabLayout.getTabAt(i).setText(this.TAB_TITLES[i]);
            tabLayout.getTabAt(i).setIcon(R.mipmap.ic_launcher_round);
        }
    }
}
