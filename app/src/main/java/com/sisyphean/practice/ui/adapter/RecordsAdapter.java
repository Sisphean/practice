package com.sisyphean.practice.ui.adapter;

import android.content.Context;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.RecordBean;

public class RecordsAdapter extends CommonAdapter<RecordBean> {
    public RecordsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_records;
    }

    @Override
    protected void convert(CommonViewHolder holder, RecordBean data) {

    }

}
