package com.ldh.mytools.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.ldh.barlibrary.activity.WebActivity;
import com.ldh.barlibrary.utils.DensityUtil;
import com.ldh.barlibrary.barTools.ImmersionBar;
import com.ldh.barlibrary.barTools.ImmersionFragment;
import com.ldh.mytools.R;
import com.ldh.mytools.activity.HomeActivity;
import com.ldh.mytools.activity.Touch;
import com.ldh.mytools.holder.HomeBanner;
import com.ldh.mytools.utils.AppUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LiaoDuanHong  on 2017/6/16
 */

public class HomeFragment extends ImmersionFragment {
    @BindView(R.id.mConvenientBanner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.mTextView)
    TextView mTextView;
    @BindView(R.id.m_ViewLian)
    View mViewLain;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    private Unbinder unbinder;
    private ArrayList<String> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", "https://www.baidu.com");
                startActivity(intent);
            }
        });
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });
        llShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Touch.class));
            }
        });
    }

    //初始化视图
    private void initView() {
        //适配mConvenientBanner
        ViewGroup.LayoutParams lp = mConvenientBanner.getLayoutParams();
        int wh[] = AppUtils.getScreenWH(getActivity());
        lp.height = Math.round(wh[0] / 1.75609756097561f);
        lp.width = wh[0];
        Log.e("宽:", lp.width + "");
        Log.e("高:", lp.height + "");
        int g = DensityUtil.dip2px(getActivity(), 48f);
        Log.e("转：", +g + "");
        ViewGroup.LayoutParams lp1 = mTextView.getLayoutParams();
        lp1.height = Math.round(wh[0] / 3.75f);
        ViewGroup.LayoutParams lp2 = mViewLain.getLayoutParams();
        lp2.height = Math.round(wh[0] / 36f);
        lp2.width = wh[0];
    }


    private void initData() {
        //这个是在添加默认的图片
        data = new ArrayList<>();
        data.add("http://upimg.ahmkj.cn/updata/img38487877.jpeg");
        data.add("http://upimg.ahmkj.cn/updata/img38524489.jpeg");
        data.add("http://upimg.ahmkj.cn/updata/img37851618.png");
        //设置banner图片
        mConvenientBanner.setPages(new CBViewHolderCreator<HomeBanner>() {
            @Override
            public HomeBanner createHolder() {
                return new HomeBanner();
            }
        }, data)//设置自动播放时间3000毫秒
                .startTurning(3000)
                //设置指示器，可以不用设置
                .setPageIndicator(new int[]{R.mipmap.ico_slider_normal, R.mipmap.ico_slider_selected})
                //设置指示器显示的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(getActivity()).statusBarAlpha(0.0f).fitsSystemWindows(false).statusBarDarkFont(false).navigationBarColor(R.color.blank).init();
    }
}
