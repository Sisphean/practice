package com.sisyphean.practice.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public class NavigationFragment extends BaseFragment<BasePresenter>{

    public static Fragment getInstance() {
        return new NavigationFragment();
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void createPresenter() {

    }
}
