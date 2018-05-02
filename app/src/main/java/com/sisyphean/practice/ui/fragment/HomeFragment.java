package com.sisyphean.practice.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.ArticlesBean;
import com.sisyphean.practice.presenter.HomePresenter;
import com.sisyphean.practice.ui.adapter.HomeAdapter;
import com.sisyphean.practice.view.IHomeView;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView{

    private View rootView;
    private RecyclerView recyclerLayout;
    private HomeAdapter homeAdapter;
    private SmartRefreshLayout refreshLayout;

    public static Fragment getInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_home, null);
        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.reqArticleList(true);
    }


    private void initView() {
        recyclerLayout = rootView.findViewById(R.id.recycler_layout);
        recyclerLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext());
        recyclerLayout.setAdapter(homeAdapter);


        refreshLayout = rootView.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.reqArticleList(true);
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.reqArticleList(false);
                refreshLayout.finishLoadMore(1000);
            }
        });
    }

    @Override
    protected void createPresenter() {
        presenter = new HomePresenter();
    }

    @Override
    public void showList(ArticlesBean articlesBean) {

        homeAdapter.addData(articlesBean.getDatas());
    }

    @Override
    public void loadMore(ArticlesBean articlesBean) {
        homeAdapter.appendData(articlesBean.getDatas());

    }

    @Override
    public void refresh() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showBanner() {

    }
}
