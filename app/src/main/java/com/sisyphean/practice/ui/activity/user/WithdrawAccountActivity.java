package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.WithdrawAccountBean;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.ui.adapter.WithdrawAccountAdapter;
import com.sisyphean.practice.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class WithdrawAccountActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_add;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            ToastUtil.show(getContext(), "xxx");
        }
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WithdrawAccountAdapter withdrawAccountAdapter = new WithdrawAccountAdapter(this);
        recyclerView.setAdapter(withdrawAccountAdapter);

        List<WithdrawAccountBean> datas = new ArrayList<>();
        datas.add(new WithdrawAccountBean());
        datas.add(new WithdrawAccountBean());
        datas.add(new WithdrawAccountBean());
        withdrawAccountAdapter.updateDatas(datas);
    }

    @Override
    protected int setToolbarTitle() {
        return R.string.tab_withdraw_account;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_account;
    }

    @Override
    protected void createPresenter() {

    }
}
