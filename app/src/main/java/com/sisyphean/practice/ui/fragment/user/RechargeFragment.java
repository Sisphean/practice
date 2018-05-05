package com.sisyphean.practice.ui.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.fragment.BaseFragment;

public class RechargeFragment extends BaseFragment {

    public static Fragment getInstance() {
        RechargeFragment rechargeFragment = new RechargeFragment();
        return rechargeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recharge, null);
        return rootView;
    }

    @Override
    protected void createPresenter() {

    }
}
