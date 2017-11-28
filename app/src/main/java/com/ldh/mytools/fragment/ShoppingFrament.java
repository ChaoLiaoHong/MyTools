package com.ldh.mytools.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.ldh.barlibrary.barTools.ImmersionBar;
import com.ldh.barlibrary.barTools.ImmersionFragment;
import com.ldh.mytools.R;
import com.ldh.mytools.utils.TimeTaskScroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LiaoDuanHong  on 2017/6/16
 */

public class ShoppingFrament extends ImmersionFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    Unbinder unbinder;
    private List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        new Timer().schedule(new TimeTaskScroll(getActivity(), mListView, list), 500, 500);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试" + i);
        }
    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(getActivity()).fitsSystemWindows(true).statusBarAlpha(0.3f).statusBarDarkFont(true).navigationBarColor(R.color.blank).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
