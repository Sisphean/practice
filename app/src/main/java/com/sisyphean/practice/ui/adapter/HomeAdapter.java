package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.ArticlesBean;

public class HomeAdapter extends CommonAdapter<ArticlesBean.DatasBean> {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_home_list;
    }

    @Override
    protected void convert(CommonViewHolder holder, ArticlesBean.DatasBean data) {
        holder.setText(R.id.textView, data.getTitle());
    }
}
