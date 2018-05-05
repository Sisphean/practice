package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.fragment.user.PayTypeFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity {

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = findViewById(R.id.viewpager);

        Fragment payTypeFragment = PayTypeFragment.getInstance();
        Fragment payTypeFragment1 = PayTypeFragment.getInstance();
        Fragment payTypeFragment2 = PayTypeFragment.getInstance();
        mFragments.add(payTypeFragment);
        mFragments.add(payTypeFragment1);
        mFragments.add(payTypeFragment2);

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

    }

    @Override
    protected void createPresenter() {

    }
}
