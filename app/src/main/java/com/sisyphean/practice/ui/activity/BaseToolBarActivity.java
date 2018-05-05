package com.sisyphean.practice.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.BasePresenter;

public abstract class BaseToolBarActivity<P extends BasePresenter> extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbar();
    }

    protected void initToolbar() {
        ImageView iv_back = findViewById(R.id.iv_back);
        TextView tv_toolbar_title = findViewById(R.id.tv_toolbar_title);
        iv_back.setOnClickListener(this);
        tv_toolbar_title.setText(setToolbarTitle());

    }

    protected abstract int setToolbarTitle();

    protected abstract int getLayoutId();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
