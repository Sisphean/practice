package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.fragment.user.PayTypeFragment;
import com.sisyphean.practice.view.user.IOrderDetailView;
import com.sisyphean.practice.widget.IndicatorLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity implements IOrderDetailView {

    private List<Fragment> mFragments = new ArrayList<>();
    private IndicatorLayout viewpager_indicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orderdetail;
    }

    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        viewpager_indicator = (IndicatorLayout) findViewById(R.id.viewpager_indicator);

        Fragment payTypeFragment = PayTypeFragment.getInstance();
        Fragment payTypeFragment1 = PayTypeFragment.getInstance();
        Fragment payTypeFragment2 = PayTypeFragment.getInstance();
        mFragments.add(payTypeFragment);
        mFragments.add(payTypeFragment1);
        mFragments.add(payTypeFragment2);

        viewpager_indicator.setCount(mFragments.size());


        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewpager_indicator.setCurPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void setUnitPrice() {

    }

    @Override
    public void setAmount() {

    }

    @Override
    public void setNumber() {

    }

    @Override
    public void setStatus() {

    }
}
