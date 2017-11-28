package com.ldh.mytools.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.ldh.barlibrary.abstracts.BaseActivity;
import com.ldh.barlibrary.barTools.ImmersionBar;
import com.ldh.barlibrary.utils.LogUtil;
import com.ldh.mytools.R;
import com.ldh.mytools.utils.RefleshAnim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.mBank)
    ImageView mBank;
    @BindView(R.id.mTitle)
    TextView mTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.mRefresh)
    TwinklingRefreshLayout mRefresh;
    @BindView(R.id.myViewAnimation)
    ImageView myViewAnimation;
    @BindView(R.id.parallax)
    ImageView parallax;
    @BindView(R.id.mScrollView)
    NestedScrollView mScrollView;
    Animation animation;
    private Bitmap bmp, zoomedBMP;
    int bmpWidth, bmpHeight;
    private static final double ZOOM_IN_SCALE = 1.2;//放大系数
    private static final double ZOOM_OUT_SCALE = 0.8;//缩小系数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ImmersionBar.with(this).fitsSystemWindows(false).statusBarAlpha(0.0f).init();
        initViews();
        initEvent();
    }

    private void initViews() {
        mTitle.setText(R.string.home);
        rlTitle.setBackgroundResource(R.color.tran);
        ViewGroup.LayoutParams layoutParams = rlTitle.getLayoutParams();
        layoutParams.height = ImmersionBar.getActionBarHeight(this) + ImmersionBar.getStatusBarHeight(this);
        rlTitle.setLayoutParams(layoutParams);
        bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_head_default);
        bmpWidth = bmp.getWidth();
        bmpHeight = bmp.getHeight();
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_img);
        myViewAnimation.setAnimation(animation);
        animation.start();
    }

    private void initEvent() {
        RefleshAnim.initLoading(this, mRefresh);
        mRefresh.setEnableLoadmore(false);
        mRefresh.setEnableRefresh(true);
        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullingDown(refreshLayout, fraction);
                parallax.setTranslationY(fraction * 1.2f * 100);
                rlTitle.setAlpha(1 - Math.min(1, fraction));

            }

            @Override
            public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullingUp(refreshLayout, fraction);
                //LogUtil.LogShitou(String.valueOf(1 + Math.min(fraction, 1)));
                //myViewAnimation.setAlpha(1 + Math.min(fraction, 1));
            }

            @Override
            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullDownReleasing(refreshLayout, fraction);
            }

            @Override
            public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullUpReleasing(refreshLayout, fraction);
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                //  myViewAnimation.setAlpha(0.3f);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
                mRefresh.setEnableRefresh(false);
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
                mRefresh.setEnableLoadmore(false);
            }
        });
        homeScrollView();
    }

    int headHeight = 0;
    private float scaleWidth = 1;
    private float scaleHeight = 1;

    private void homeScrollView() {
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtil.LogShitou("X:" + String.valueOf((scrollX)));
                LogUtil.LogShitou("y:" + String.valueOf((scrollY)));
                LogUtil.LogShitou("oldScrollY:" + String.valueOf((oldScrollY)));
                headHeight = rlTitle.getHeight();
                LogUtil.LogShitou("rlTitle:" + rlTitle.getHeight());
                if (scrollY <= 0) {
                    rlTitle.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));//AGB由相关工具获得，或者美工提供
                } else if (scrollY > 0 && scrollY <= headHeight) {
                    float scale = (float) scrollY / headHeight;
                    LogUtil.LogShitou("滑动时的透明值：" + scale);
                    float alpha = 0.0f;
                    // 只是layout背景透明(仿知乎滑动效果)
                    rlTitle.setBackgroundColor(Color.argb((int) alpha, 61, 123, 206));
                } else {
                    rlTitle.setBackgroundColor(Color.argb((int) 255, 61, 123, 206));
                }
                myViewAnimation.setTranslationX(-scrollY * 3);
                myViewAnimation.setTranslationY(0);
//                if (scrollY > oldScrollY) {
//                    small();
//                } else if (scrollY == 0) {
//                    odlImg();
//                }else {
//                    enlarge();
//                }
            }
        });
    }

    //按钮点击缩小函数
    private void small() {
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();

            scaleWidth = (float) (scaleWidth * ZOOM_OUT_SCALE);
            scaleHeight = (float) (scaleHeight * ZOOM_OUT_SCALE);

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            zoomedBMP = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix,
                    true);
            myViewAnimation.setImageBitmap(zoomedBMP);
        } catch (Exception e) {
            //can't zoom because of memory issue, just ignore, no big deal
        }
    }
    //图片放大
    private void enlarge() {
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();

            scaleWidth = (float) (scaleWidth * ZOOM_IN_SCALE);
            scaleHeight = (float) (scaleHeight * ZOOM_IN_SCALE);

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            zoomedBMP = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix,
                    true);
            myViewAnimation.setImageBitmap(zoomedBMP); //存在内存溢出问题

        } catch (Exception e) {
            //can't zoom because of memory issue, just ignore, no big deal
        }
    }
   //鱼片还原
    private void odlImg(){
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();

            scaleWidth = (float) bmpWidth;
            scaleHeight = (float) bmpHeight;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            zoomedBMP = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix,
                    true);
            myViewAnimation.setImageBitmap(zoomedBMP); //存在内存溢出问题

        } catch (Exception e) {
            //can't zoom because of memory issue, just ignore, no big deal
        }
    }
    @OnClick(R.id.mBank)
    public void onViewClicked() {
        finish();
    }
}
