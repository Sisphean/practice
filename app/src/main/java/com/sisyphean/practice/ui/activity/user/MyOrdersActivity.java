package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.MyOrderBean;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.adapter.MyOrderAdapter;
import com.sisyphean.practice.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends BaseToolBarActivity {

    private MyOrderAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyOrderAdapter(this);
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
        return R.menu.menu_add;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            ToastUtil.show(this, "添加订单");
        }
    }

    @Override
    protected int setToolbarTitle() {
        return R.string.tab_my_order;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_list;
    }

    @Override
    protected void createPresenter() {

    }
}
