package com.sisyphean.practice.ui.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.fragment.BaseFragment;

public class RechargeListFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {

    }

    @Override
    protected void createPresenter() {

    }
}
