package com.sisyphean.practice.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;
import com.sisyphean.practice.ui.fragment.user.BonusFragment;
import com.sisyphean.practice.ui.fragment.user.RecordsFragment;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeSysFragment extends BaseFragment<BasePresenter> {

    public static Fragment getInstance(){
        return new KnowledgeSysFragment();
    }

    @Override
    protected void initView(View rootView) {
        SmartRefreshLayout refreshLayout = rootView.findViewById(R.id.refresh_layout);
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        SlidingTabLayout indacator = rootView.findViewById(R.id.viewpager_indicator);

        final List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(BonusFragment.getInstance());
        mFragments.add(BonusFragment.getInstance());
        mFragments.add(BonusFragment.getInstance());
        mFragments.add(BonusFragment.getInstance());

        final String[] mTabs = {"tab1", "tab2", "tab3", "tab4"};

        viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
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
                return mTabs[position];
            }
        });

        indacator.setViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_system;
    }

    @Override
    protected void createPresenter() {

    }
}
