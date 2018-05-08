package com.sisyphean.practice.ui.fragment.user;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.RechargeBean;
import com.sisyphean.practice.ui.adapter.RechargeAdapter;
import com.sisyphean.practice.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RechargeListFragment extends BaseFragment {

    public static Fragment getInstance() {
        RechargeListFragment rechargeListFragment = new RechargeListFragment();
        return rechargeListFragment;
    }

    @Override
    protected void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RechargeAdapter rechargeAdapter = new RechargeAdapter(getContext());
        recyclerView.setAdapter(rechargeAdapter);

        List<RechargeBean> datas = new ArrayList<>();
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());
        datas.add(new RechargeBean());

        rechargeAdapter.updateDatas(datas);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_list;
    }

    @Override
    protected void createPresenter() {

    }
}
