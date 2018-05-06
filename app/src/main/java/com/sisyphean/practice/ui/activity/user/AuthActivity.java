package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;

public class AuthActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_auth;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void createPresenter() {

    }



}
