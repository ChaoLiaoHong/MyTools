package com.ldh.mytools.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.ldh.barlibrary.barTools.ImmersionBar;
import com.ldh.barlibrary.barTools.ImmersionFragment;
import com.ldh.barlibrary.utils.LogUtil;
import com.ldh.barlibrary.views.UpDownTextView;
import com.ldh.mytools.R;
import com.ldh.mytools.adapter.MultiTypeViewBinder;
import com.ldh.mytools.adapter.ViewHolder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by LiaoDuanHong  on 2017/6/16
 */

public class CircleFragment extends ImmersionFragment {
    @BindView(R.id.marquee)
    UpDownTextView marquee;
    Unbinder unbinder;
    @BindView(R.id.lln)
    LinearLayout lln;
    @BindView(R.id.btn_dj)
    Button btn_dj;
    private int TIME = 2000;
    private int index = 0;
    ArrayList<String> dataList = new ArrayList<>();
    private MultiTypeAdapter mMultiTypeAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //index是全局的，判断小于list长度，防止数组越界异常
                    if (index < dataList.size()) {
                        index++;
                        if (index == dataList.size()) {
                            index = 0;
                        }
                    }
                    //给TextView赋值
                    if (dataList.get(index) != null && !dataList.get(index).equals(""))
                        marquee.setText(dataList.get(index));
                    //自己调用自己（mHandler）
                    mHandler.sendEmptyMessageDelayed(1, TIME);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        getData();
        myOnClick();
        return view;
    }

    private void initData() {
        mMultiTypeAdapter = new MultiTypeAdapter();
        MultiTypeViewBinder<String> multiTypeViewBinder = new MultiTypeViewBinder<String>(getActivity(), R.layout.fragment_circle) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
               holder.getView(R.id.btn_dj);
            }
        };
        multiTypeViewBinder.setOnItemClickListener(new MultiTypeViewBinder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }
        });
    }


    @Override
    protected void immersionInit() {
        ImmersionBar.with(getActivity()).statusBarAlpha(0.3f).fitsSystemWindows(true).statusBarDarkFont(true).navigationBarColor(R.color.blank).init();
    }

    private void initView() {

    }

    private void myOnClick() {
        lln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "你点击了" + marquee.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeMessages(1);
        unbinder.unbind();
    }

    private void getData() {
        dataList.add("1111111111");
        dataList.add("2222222222");
        dataList.add("3333333333");
        mHandler.sendEmptyMessage(1);
    }
}
