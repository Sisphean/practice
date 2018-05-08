package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.BonusBean;

public class BonusAdapter extends CommonAdapter<BonusBean> {

    public BonusAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_bonus;
    }

    @Override
    protected void convert(CommonViewHolder holder, BonusBean data) {

    }
}
