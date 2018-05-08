package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.RechargeBean;

public class RechargeAdapter extends CommonAdapter<RechargeBean> {

    public RechargeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_recharge;
    }

    @Override
    protected void convert(CommonViewHolder holder, RechargeBean data) {

    }
}
