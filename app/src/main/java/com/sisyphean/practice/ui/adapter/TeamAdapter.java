package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.TeamBean;

public class TeamAdapter extends CommonAdapter<TeamBean> {

    public TeamAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_team;
    }

    @Override
    protected void convert(CommonViewHolder holder, TeamBean data) {

    }
}
