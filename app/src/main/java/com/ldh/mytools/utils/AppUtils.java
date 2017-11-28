package com.ldh.mytools.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;

import com.ldh.mytools.BuildConfig;

/**
 * Created by LiaoDuanHong  on 2017/6/19
 */

public class AppUtils {
    /**
     * 获取屏幕的宽和高
     *
     * @param activity
     * @return screenWH[0]:width 和 screenWH[1]:height
     */
    public static int[] getScreenWH(Activity activity) {
        int[] screenWH = new int[2];
        Display display = activity.getWindowManager().getDefaultDisplay();
        screenWH[0] = display.getWidth();
        screenWH[1] = display.getHeight();
        return screenWH;
    }

    /**
     * 描述：判断网络是否有效.
     *
     * @param context the context
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
