package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.fragment.KnowledgeSysFragment;
import com.sisyphean.practice.ui.fragment.user.RechargeFragment;
import com.sisyphean.practice.ui.fragment.user.RechargeListFragment;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends BaseToolBarActivity {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout mIndicator = (SlidingTabLayout) findViewById(R.id.viewpager_indicator);

        mFragments.add(RechargeListFragment.getInstance());
        mFragments.add(RechargeFragment.getInstance());

        mFragmentTitles.add("买入");
        mFragmentTitles.add("充值");

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitles.get(position);
            }
        });

        mIndicator.setViewPager(mViewPager);

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_recharge;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void createPresenter() {

    }
}
