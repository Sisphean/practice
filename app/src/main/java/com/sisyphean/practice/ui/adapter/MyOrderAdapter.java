package com.sisyphean.practice.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.MyOrderBean;
import com.sisyphean.practice.ui.activity.user.OrdersActivity;
import com.sisyphean.practice.widget.SwipeLayout;

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
    protected void convert(CommonViewHolder holder, final MyOrderBean data) {
        holder.setOnClick(R.id.btn_order, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersActivity.class);
                context.startActivity(intent);
            }
        });

        final SwipeLayout swipeLayout = holder.getView(R.id.swipe_layout);
        RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recycler_layout);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    swipeLayout.smoothSlideToClose();
                }
            }
        });

        holder.setOnClick(R.id.tv_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDatas(data);
            }
        });
    }
}
