package com.ldh.barlibrary.barTools;

import android.support.v4.app.Fragment;

/**
 * ImmersionFragment沉浸式基类，因为fragment是基于activity之上的，
 * 为了能够在fragment使用沉浸式而fragment之间又相互不影响，必须实现immersionInit方法，
 * 原理是当用户可见才执行沉浸式初始化
 *
 * Created by LiaoDuanHong on 2017/7/10.
 */
public abstract class ImmersionFragment extends Fragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            immersionInit();
        }
    }

    protected abstract void immersionInit();
}
