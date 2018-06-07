package com.sisyphean.practice.ui.fragment.user;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.RecordBean;
import com.sisyphean.practice.ui.adapter.RecordsAdapter;
import com.sisyphean.practice.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordsFragment extends BaseFragment {

    public static Fragment getInstance() {
        RecordsFragment recordsFragment = new RecordsFragment();
        return recordsFragment;
    }

    @Override
    protected void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecordsAdapter recordsAdapter = new RecordsAdapter(getContext());
        recyclerView.setAdapter(recordsAdapter);

        SmartRefreshLayout refreshLayout = rootView.findViewById(R.id.refresh_layout);
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                List<RecordBean> datas = new ArrayList<>();

                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());
                datas.add(new RecordBean());

                recordsAdapter.updateDatas(datas);
                refreshLayout.finishRefresh();
            }
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_list;
    }

    @Override
    protected void createPresenter() {

    }
}
