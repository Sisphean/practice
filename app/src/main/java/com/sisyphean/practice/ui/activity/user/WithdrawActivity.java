package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.WithdrawPresenter;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.fragment.user.WithdrawFragment;
import com.sisyphean.practice.ui.fragment.user.WithdrawListFragment;

import java.util.ArrayList;
import java.util.List;

public class WithdrawActivity extends BaseToolBarActivity {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] titles = {"卖出", "提现"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_withdraw;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout mIndicator = (SlidingTabLayout) findViewById(R.id.viewpager_indicator);

        mFragments.add(WithdrawListFragment.getInstance());
        mFragments.add(WithdrawFragment.getInstance());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        mIndicator.setViewPager(mViewPager);

    }

    @Override
    protected void createPresenter() {
        mPresenter = new WithdrawPresenter();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        }
    }

}
