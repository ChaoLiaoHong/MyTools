package com.ldh.barlibrary.abstracts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ldh.barlibrary.barTools.ImmersionBar;

/**
 * Created by LiaoDuanHong  on 2017/7/10
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarAlpha(0.3f).init();//初始化状态栏
        //数据获取
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            readSaveBundle(mBundle);
        } else if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle(getClass().getSimpleName());
            readSaveBundle(mBundle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    /**
     * 不为空的数据
     */
    public void readSaveBundle(Bundle bundle) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mBundle != null){ outState.putBundle(getClass().getSimpleName(), mBundle);}
        super.onSaveInstanceState(outState);
    }
}
