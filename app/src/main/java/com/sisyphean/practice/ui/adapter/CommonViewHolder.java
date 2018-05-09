package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    SparseArray<View> mViews;
    View itemView;


    public CommonViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        this.itemView = itemView;
    }

    public static CommonViewHolder create(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    public void put(int resId) {
        View view = itemView.findViewById(resId);
        if (view != null) {
            mViews.put(resId, view);
        }
    }

    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            put(resId);
        }
        return (T) view;
    }

    public RecyclerView.ViewHolder setImage(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public RecyclerView.ViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public RecyclerView.ViewHolder setOnClick(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
        return this;
    }


}
