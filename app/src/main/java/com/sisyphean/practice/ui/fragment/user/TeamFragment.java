package com.sisyphean.practice.ui.fragment.user;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.TeamBean;
import com.sisyphean.practice.ui.adapter.TeamAdapter;
import com.sisyphean.practice.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends BaseFragment {

    public static Fragment getInstance() {
        TeamFragment teamFragment = new TeamFragment();
        return teamFragment;
    }

    @Override
    protected void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TeamAdapter teamAdapter = new TeamAdapter(getActivity());
        recyclerView.setAdapter(teamAdapter);

        List<TeamBean> datas = new ArrayList<>();
        datas.add(new TeamBean());
        datas.add(new TeamBean());
        datas.add(new TeamBean());
        datas.add(new TeamBean());
        datas.add(new TeamBean());
        datas.add(new TeamBean());

        teamAdapter.updateDatas(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void createPresenter() {

    }
}
