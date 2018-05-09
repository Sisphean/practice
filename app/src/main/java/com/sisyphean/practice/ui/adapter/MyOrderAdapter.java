package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.MyOrderBean;
import com.sisyphean.practice.ui.activity.user.OrdersActivity;

public class MyOrderAdapter extends CommonAdapter<MyOrderBean> {
    private Context context;

    public MyOrderAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_my_order;
    }

    @Override
    protected void convert(CommonViewHolder holder, MyOrderBean data) {
        holder.setOnClick(R.id.btn_order, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
