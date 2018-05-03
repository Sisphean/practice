package com.sisyphean.practice.ui.activity.logon;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseActivity;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void createPresenter() {

    }
}
