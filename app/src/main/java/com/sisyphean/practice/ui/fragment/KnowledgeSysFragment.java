package com.sisyphean.practice.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public class KnowledgeSysFragment extends BaseFragment<BasePresenter> {

    public static Fragment getInstance(){
        return new KnowledgeSysFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_knowledge_system, null);
    }

    @Override
    protected void createPresenter() {

    }
}
