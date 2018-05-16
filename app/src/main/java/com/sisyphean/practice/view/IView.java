package com.sisyphean.practice.view;

import android.content.Context;

public interface IView {

    Context getContext();

    void showLoading(String msg);

    void hideLoading();

    void showToast(String msg);

    void setTitle();

}
