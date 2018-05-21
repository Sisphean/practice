package com.sisyphean.practice.ui.fragment.user;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.BonusBean;
import com.sisyphean.practice.ui.adapter.BonusAdapter;
import com.sisyphean.practice.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class BonusFragment extends BaseFragment {

    public static Fragment getInstance() {
        BonusFragment bonusFragment = new BonusFragment();
        return bonusFragment;
    }

    @Override
    protected void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BonusAdapter bonusAdapter = new BonusAdapter(getActivity());
        recyclerView.setAdapter(bonusAdapter);

        List<BonusBean> datas = new ArrayList<>();
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        datas.add(new BonusBean());
        bonusAdapter.updateDatas(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void createPresenter() {

    }
}
