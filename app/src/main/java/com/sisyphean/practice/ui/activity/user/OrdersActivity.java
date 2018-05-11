package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.MyOrderBean;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.adapter.OrdersAdapter;
import com.sisyphean.practice.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends BaseToolBarActivity {

    private OrdersAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrdersAdapter(this);
        recyclerView.setAdapter(adapter);

        List<MyOrderBean> datas = new ArrayList<>();
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        datas.add(new MyOrderBean());
        adapter.updateDatas(datas);
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_order;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_list;
    }

    @Override
    protected void createPresenter() {

    }
}
