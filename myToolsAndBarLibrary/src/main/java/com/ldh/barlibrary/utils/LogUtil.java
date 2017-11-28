package com.ldh.barlibrary.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 *Created by LiaoDuanHong on 2017/7/10.
 */

public class LogUtil {

    //可以全局控制是否打印log日志
    private static boolean isPrintLog = true;

    private static int LOG_MAXLENGTH = 2000;

    public static void LogShitou(String msg) {
        if (TextUtils.isEmpty(msg)) msg = "你传个空进来做啥？想报异常？";
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e("输出" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e("输出" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void LogShitou(String type, String msg) {
        if (TextUtils.isEmpty(msg)) msg = "你传个空进来做啥？想报异常？";
        if (isPrintLog) {

            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(type + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(type + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

}