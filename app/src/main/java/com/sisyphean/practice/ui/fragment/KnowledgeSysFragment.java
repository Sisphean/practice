package com.sisyphean.practice.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public class KnowledgeSysFragment extends BaseFragment<BasePresenter> {

    public static Fragment getInstance(){
        return new KnowledgeSysFragment();
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_system;
    }

    @Override
    protected void createPresenter() {

    }
}
