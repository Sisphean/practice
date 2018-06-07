package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sisyphean.practice.R;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private Context context;
    private int layoutId;
    private int emptyLayoutId;
    private List<T> mDatas = new ArrayList<>();
    private static final int VIEWTYPE_NORMAL = 1;
    private static final int VIEWTYPE_NO_DATA = 2;

    public CommonAdapter(Context context) {
        this.context = context;
        this.layoutId = getItemLayoutId();
        this.emptyLayoutId = getEmptyLayoutId();
    }

    protected int getEmptyLayoutId() {
        return R.layout.layout_empty;
    }

    protected abstract int getItemLayoutId();

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEWTYPE_NORMAL)
            return CommonViewHolder.create(context, parent, layoutId);
        else
            return emptyLayoutId == 0 ? CommonViewHolder.create(context, parent, R.layout.layout_empty)
                    : CommonViewHolder.create(context, parent, getEmptyLayoutId());
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        if (mDatas.size() > 0)
            convert(holder, mDatas.get(position));
    }

    protected abstract void convert(CommonViewHolder holder, T data);

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.size() == 0 ? 1 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() > 0) {
            return VIEWTYPE_NORMAL;
        } else {
            return VIEWTYPE_NO_DATA;
        }

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
