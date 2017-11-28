package com.ldh.mytools.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ldh.barlibrary.abstracts.BaseActivity;
import com.ldh.barlibrary.views.NoScrollViewPager;
import com.ldh.mytools.R;
import com.ldh.mytools.adapter.MainActivityViewPagerAdapter;
import com.ldh.mytools.fragment.CircleFragment;
import com.ldh.mytools.fragment.HomeFragment;
import com.ldh.mytools.fragment.MeFragment;
import com.ldh.mytools.fragment.ShoppingFrament;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mViewPager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.mHome)
    RadioButton mHome;
    @BindView(R.id.mShopping)
    RadioButton mShopping;
    @BindView(R.id.mCircle)
    RadioButton mCircle;
    @BindView(R.id.mMe)
    RadioButton mMe;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        initViewPager();
        initEvent();
    }
    //设置事件（监听）函数（函数也叫方法）

    /**
     * 自己创建的initEvent（）方法和initViewPager（）方法
     */
    private void initEvent() {
        mViewPager.addOnPageChangeListener(new onPageChangeListener());
    }

    //初始化fragment
    private void initViewPager() {
        //初始化4个fragment
        HomeFragment mHomeFragment = new HomeFragment();
        ShoppingFrament mShoppingFragment = new ShoppingFrament();
        CircleFragment mCircleFragment = new CircleFragment();
        MeFragment mMeFragment = new MeFragment();
        //把Fragment装进集合
        fragmentList.add(mHomeFragment);
        fragmentList.add(mShoppingFragment);
        fragmentList.add(mCircleFragment);
        fragmentList.add(mMeFragment);
        //viewPager适配器
        mViewPager.setAdapter(new MainActivityViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        //每次启动app默认显示首页Fragment
        setFirst();
        mViewPager.setCurrentItem(0, false);
    }

    //设置每次启动app时显示首页Fragment及首个RadioButton
    private void setFirst() {
        mRadioGroup.check(R.id.mHome);
        mHome.setCompoundDrawables(null, setBounds(R.mipmap.ico_hone_selected), null, null);
        mShopping.setCompoundDrawables(null, setBounds(R.mipmap.gouwu), null, null);
        mCircle.setCompoundDrawables(null, setBounds(R.mipmap.ico_quanzi_selected), null, null);
        mMe.setCompoundDrawables(null, setBounds(R.mipmap.wode), null, null);
    }

    //点击某个RadioButton切换Fragment
    @OnClick({R.id.mHome, R.id.mShopping, R.id.mCircle, R.id.mMe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mHome:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.mShopping:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.mCircle:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.mMe:
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    //切换fragment的监听改变RadioButton的图标
    private class onPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    setFirst();
                    break;
                case 1:
                    mRadioGroup.check(R.id.mShopping);
                    mHome.setCompoundDrawables(null, setBounds(R.mipmap.shouye), null, null);
                    mShopping.setCompoundDrawables(null, setBounds(R.mipmap.ico_shopping_selectd), null, null);
                    mCircle.setCompoundDrawables(null, setBounds(R.mipmap.ico_quanzi_selected), null, null);
                    mMe.setCompoundDrawables(null, setBounds(R.mipmap.wode), null, null);
                    break;
                case 2:
                    mRadioGroup.check(R.id.mCircle);
                    mHome.setCompoundDrawables(null, setBounds(R.mipmap.shouye), null, null);
                    mShopping.setCompoundDrawables(null, setBounds(R.mipmap.gouwu), null, null);
                    mCircle.setCompoundDrawables(null, setBounds(R.mipmap.quanzi), null, null);
                    mMe.setCompoundDrawables(null, setBounds(R.mipmap.wode), null, null);
                    break;
                case 3:
                    mRadioGroup.check(R.id.mMe);
                    mHome.setCompoundDrawables(null, setBounds(R.mipmap.shouye), null, null);
                    mShopping.setCompoundDrawables(null, setBounds(R.mipmap.gouwu), null, null);
                    mCircle.setCompoundDrawables(null, setBounds(R.mipmap.ico_quanzi_selected), null, null);
                    mMe.setCompoundDrawables(null, setBounds(R.mipmap.ico_wode_selected), null, null);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //设置按钮图标大小
    private Drawable setBounds(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    //监听手机自带返回键两次的退出程序
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
