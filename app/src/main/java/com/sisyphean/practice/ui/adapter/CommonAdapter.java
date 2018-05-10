package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private Context context;
    private int layoutId;
    private List<T> mDatas = new ArrayList<>();

    public CommonAdapter(Context context) {
        this.context = context;
        this.layoutId = getItemLayoutId();
    }

    protected abstract int getItemLayoutId();

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CommonViewHolder.create(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        convert(holder, mDatas.get(position));

    }

    protected abstract void convert(CommonViewHolder holder, T data);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void updateDatas(List<T> datas) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void appendDatas(List<T> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void removeDatas(T data) {
        if (mDatas != null) {
            mDatas.remove(data);
            notifyDataSetChanged();
        }
    }

}
