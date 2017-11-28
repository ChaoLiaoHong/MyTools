package com.ldh.mytools.holder;

import android.content.Context;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.ldh.mytools.R;

/**
 * Created by LiaoDuanHong  on 2017/6/19
 */

public class HomeBanner implements Holder<String> {
    private ImageView imageView;

    @Override
    public ImageView createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context).load(data).crossFade().placeholder(R.mipmap.logo).error(R.mipmap.ic_launcher).into(imageView);
    }
}
