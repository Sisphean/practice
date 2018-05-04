package com.sisyphean.practice.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sisyphean.practice.presenter.BasePresenter;
import com.sisyphean.practice.view.IView;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {

    private static final String TAG = "Fragment";

    ProgressDialog loadingDialog;

    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onViewCreated(view, savedInstanceState);
        createPresenter();
        attachView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisisbleHint() is called. Fragment: " + this.hashCode() + ". status: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onDestroyView();
        detachView();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach() is called. Fragment: " + this.hashCode());
        Log.d(TAG, "getUserVisibleHint() = " + getUserVisibleHint() + ". Fragment: " + this.hashCode());
        super.onDetach();
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void createPresenter();

    @Override
    public void showLoading(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this.getActivity());
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
}
