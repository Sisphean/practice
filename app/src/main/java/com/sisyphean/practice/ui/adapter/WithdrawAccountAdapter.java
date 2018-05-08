package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.WithdrawAccountBean;

public class WithdrawAccountAdapter extends CommonAdapter<WithdrawAccountBean> {

    public WithdrawAccountAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_withdraw_account;
    }

    @Override
    protected void convert(CommonViewHolder holder, WithdrawAccountBean data) {

    }
}
