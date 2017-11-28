package com.ldh.barlibrary.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by LiaoDuanHong  on 2017/7/10
 */

public class MyApplication extends Application {
    public Context mContext;
    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
}
