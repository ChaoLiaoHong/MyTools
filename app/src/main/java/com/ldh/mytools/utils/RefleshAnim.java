package com.ldh.mytools.utils;

import android.content.Context;
import android.os.Handler;

import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.ldh.mytools.R;

public class RefleshAnim {
    public static void initLoading(Context context, TwinklingRefreshLayout tk) {
        SinaRefreshView iHeaderView=new SinaRefreshView(context);
        iHeaderView.setArrowResource(R.drawable.black_arrow);
        tk.setHeaderView(iHeaderView);
        tk.setBottomView(new BottomProgressView(context));
    }

    public static  void stopLoadmoreInRund(final TwinklingRefreshLayout tks){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tks.finishLoadmore();
            }
        });
    }
}