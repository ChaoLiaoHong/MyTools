package com.ldh.barlibrary.utils;

import android.widget.Toast;

/**
 * Created by LiaoDuanHong on 2017/7/10.
 */
public class ToastUtil {
    public static Toast toast;

    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance().mContext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
