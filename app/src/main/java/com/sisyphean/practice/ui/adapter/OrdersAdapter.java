package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.MyOrderBean;

public class OrdersAdapter extends CommonAdapter<MyOrderBean> {
    public OrdersAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_order;
    }

    @Override
    protected void convert(CommonViewHolder holder, MyOrderBean data) {

    }
}
