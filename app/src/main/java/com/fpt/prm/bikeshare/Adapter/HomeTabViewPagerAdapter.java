package com.fpt.prm.bikeshare.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeTabViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public HomeTabViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<>();
    }

    public void addItem(Fragment f) {
        this.fragmentList.add(f);
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}
