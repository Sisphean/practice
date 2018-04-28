package com.sisyphean.practice.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sisyphean.practice.presenter.BasePresenter;
import com.sisyphean.practice.view.IView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private ProgressDialog loadingDialog;

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        attachView();
    }

    private void attachView() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }

    private void detachView() {
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected abstract void createPresenter();

    @Override
    public void showLoading(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            if (!TextUtils.isEmpty(msg)) {
                loadingDialog.setMessage(msg);
            }
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
