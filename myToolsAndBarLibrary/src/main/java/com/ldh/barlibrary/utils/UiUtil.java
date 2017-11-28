package com.ldh.barlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * 常用视图操作工具栏
 * Created by LiaoDuanHong on 2017/7/10.
 */
public class UiUtil {

    /**
     * 启动浏览器intent
     *
     * @param context
     * @param url
     */
    public static void startWebIntent(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception ex) {
            ToastUtil.showToast("对不起,我们找不到任何应用程序用于查看该url !");
        }
    }

    /**
     * 开启分享Intent
     *
     * @param content 分享的内容
     */
    public static void startShareIntent(Activity context, String content) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain"); // 分享的数据类型
            intent.putExtra(Intent.EXTRA_SUBJECT, context.getTitle()); // 主题
            intent.putExtra(Intent.EXTRA_TEXT, content); // 内容
            intent = Intent.createChooser(intent, "朋友分享");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception ex) {
            ToastUtil.showToast("对不起,我们找不到分享程序!");
        }
    }

    /**
     * 本地照片分享
     *
     * @param photoUri 全路径名
     * @param activity
     */
    public static void SharePhoto(String photoUri, final Activity activity, String content) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        File file = new File(photoUri);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        shareIntent.putExtra(Intent.EXTRA_TEXT, content); // 内容
        activity.startActivity(Intent.createChooser(shareIntent, activity.getTitle()));
    }
}
