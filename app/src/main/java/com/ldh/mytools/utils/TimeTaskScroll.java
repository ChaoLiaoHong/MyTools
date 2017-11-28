package com.ldh.mytools.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;


import com.ldh.mytools.adapter.ScrollListViewAdapter;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by LiaoDuanHong  on 2017/6/26
 */

public class TimeTaskScroll extends TimerTask {
    private ListView listView;

    public TimeTaskScroll(Context context, ListView listView, List<String> list) {
        this.listView = listView;
        listView.setAdapter(new ScrollListViewAdapter(context, list));
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            listView.smoothScrollBy(10, 15);
        }
    };

    @Override
    public void run() {
        Message message = handler.obtainMessage();
        handler.sendMessage(message);
    }
}
