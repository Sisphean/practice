package com.sisyphean.practice.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public class NavigationFragment extends BaseFragment<BasePresenter>{

    public static Fragment getInstance() {
        return new NavigationFragment();
    }

    @Override
    protected void initView(View rootView) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.add(R.id.fragment_container_1, EditTextFragment.getInstance(), "edittext");
        ft.add(R.id.fragment_container_2, TextViewFragment.getInstance(), "textview");
        ft.commitAllowingStateLoss();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void createPresenter() {

    }
}
