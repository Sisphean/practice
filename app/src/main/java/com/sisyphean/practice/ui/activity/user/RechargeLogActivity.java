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
import com.sisyphean.practice.ui.fragment.user.RecordsFragment;

import java.util.ArrayList;
import java.util.List;

public class RechargeLogActivity extends BaseToolBarActivity {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mFragmentTabs = {"卖出", "提现"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = findViewById(R.id.viewpager);
        SlidingTabLayout mIndicator = findViewById(R.id.viewpager_indicator);

        mFragments.add(RecordsFragment.getInstance());
        mFragments.add(RecordsFragment.getInstance());

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
                return mFragmentTabs[position];
            }
        });
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
        return R.string.tab_record_recharge;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void createPresenter() {

    }
}
