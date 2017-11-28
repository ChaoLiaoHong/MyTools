package com.ldh.mytools.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldh.barlibrary.barTools.ImmersionBar;
import com.ldh.barlibrary.barTools.ImmersionFragment;
import com.ldh.mytools.R;


/**
 * Created by LiaoDuanHong  on 2017/6/16
 */

public class MeFragment extends ImmersionFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(getActivity()).fitsSystemWindows(true).statusBarAlpha(0.3f).statusBarDarkFont(true).navigationBarColor(R.color.blank).init();
    }
}
