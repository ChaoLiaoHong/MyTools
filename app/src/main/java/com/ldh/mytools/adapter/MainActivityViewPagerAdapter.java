package com.ldh.mytools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by LiaoDuanHong  on 2017/6/16
 */

public class MainActivityViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList;

    public MainActivityViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        Log.e("fragment总数：", String.valueOf(fragmentArrayList.size()));
    }


    @Override

    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }
}
