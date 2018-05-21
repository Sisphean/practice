package com.sisyphean.practice.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.fragment.ProjectFragment;
import com.sisyphean.practice.ui.fragment.user.BonusFragment;
import com.sisyphean.practice.ui.fragment.user.TeamFragment;
import com.sisyphean.practice.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class PromoteActivity extends BaseToolBarActivity {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mFragmentTabs = {"奖金记录", "我的团队"};

    @Override
    protected void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout mIndicator = (SlidingTabLayout) findViewById(R.id.viewpager_indicator);

        mFragments.add(BonusFragment.getInstance());
        mFragments.add(TeamFragment.getInstance());

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

        mIndicator.setViewPager(mViewPager);
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_qrcode;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {
        if (item.getItemId() == R.id.action_qr_code) {
            Intent intent = new Intent(this, QRCodeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected int setToolbarTitle() {
        return R.string.tab_promote;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promote;
    }

    @Override
    protected void createPresenter() {

    }
}
