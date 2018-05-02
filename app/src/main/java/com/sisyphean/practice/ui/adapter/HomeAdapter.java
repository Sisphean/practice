package com.sisyphean.practice.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.ArticlesBean;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    Context context;
    List<ArticlesBean.DatasBean> data = new ArrayList<>();

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_home_list, null);
        HomeViewHolder homeViewHolder = new HomeViewHolder(itemView);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public HomeViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
        }
    }

    public void addData(List<ArticlesBean.DatasBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(List<ArticlesBean.DatasBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
