package com.sisyphean.practice.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sisyphean.practice.presenter.BasePresenter;
import com.sisyphean.practice.view.IView;
import com.sisyphean.practice.widget.SwipeBackHelper;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity<P extends BasePresenter> extends SwipeBackActivity implements IView {

    private ProgressDialog loadingDialog;

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        createPresenter();
        attachView();
    }

    protected abstract int getLayoutId();

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
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
