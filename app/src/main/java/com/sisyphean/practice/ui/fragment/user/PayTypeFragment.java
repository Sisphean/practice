package com.sisyphean.practice.ui.fragment.user;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.fragment.BaseFragment;

public class PayTypeFragment extends BaseFragment {

    public static Fragment getInstance() {
        PayTypeFragment payTypeFragment = new PayTypeFragment();
        return payTypeFragment;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_type;
    }

    @Override
    protected void createPresenter() {

    }
}
