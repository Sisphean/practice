package com.sisyphean.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public class NavigationFragment extends BaseFragment<BasePresenter>{

    public static Fragment getInstance() {
        return new NavigationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_navigation, null);
    }

    @Override
    protected void createPresenter() {

    }
}
