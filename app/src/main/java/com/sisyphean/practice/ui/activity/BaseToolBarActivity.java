package com.sisyphean.practice.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;
import com.sisyphean.practice.utils.ToastUtil;

public abstract class BaseToolBarActivity<P extends BasePresenter> extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() == 0) {
            return false;
        }
        getMenuInflater().inflate(getMenuId(), menu);
        return true;
    }

    protected abstract int getMenuId();

    protected void initToolbar() {
        ImageView iv_back = findViewById(R.id.iv_back);
        Toolbar toolbar = findViewById(R.id.toolbar_layout);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onMenuItemClickListener(item);
                return true;
            }
        });
        TextView tv_toolbar_title = findViewById(R.id.tv_toolbar_title);
        iv_back.setOnClickListener(this);
        tv_toolbar_title.setText(setToolbarTitle());

    }

    protected abstract void onMenuItemClickListener(MenuItem item);

    protected abstract int setToolbarTitle();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
