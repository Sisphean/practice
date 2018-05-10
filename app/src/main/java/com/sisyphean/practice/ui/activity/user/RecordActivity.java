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

public class RecordActivity extends BaseToolBarActivity {

    public static final String KEY_TYPE = "type";
    public static final int TYPE_RECHARGE = 0;
    public static final int TYPE_WITHDRAW = 1;
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mFragmentTabs = null;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        type = getIntent().getIntExtra(KEY_TYPE, -1);
        if (type < 0) {
            throw new IllegalArgumentException("invalid type");
        }
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout mIndicator = (SlidingTabLayout) findViewById(R.id.viewpager_indicator);

        if (type == TYPE_RECHARGE) {
            mFragmentTabs = new String[]{"买入", "充值"};
        } else if (type == TYPE_WITHDRAW) {
            mFragmentTabs = new String[]{"卖出", "提现"};
        }

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

        mIndicator.setViewPager(mViewPager);
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
        if (type == TYPE_RECHARGE) {
            return R.string.tab_record_recharge;
        } else if (type == TYPE_WITHDRAW) {
            return R.string.tab_record_withdraw;
        }
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void createPresenter() {

    }
}
