package com.sisyphean.practice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.sisyphean.practice.view.IView;

public class BaseActivity extends AppCompatActivity implements IView{

    @Override
    public Context getContext() {
        return this;
    }
}
