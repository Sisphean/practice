package com.sisyphean.practice.ui.fragment.user;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.fragment.BaseFragment;

public class RechargeFragment extends BaseFragment {

    public static Fragment getInstance() {
        RechargeFragment rechargeFragment = new RechargeFragment();
        return rechargeFragment;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void createPresenter() {

    }
}
