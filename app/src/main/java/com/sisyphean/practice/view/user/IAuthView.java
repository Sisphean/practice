package com.sisyphean.practice.view.user;

import android.graphics.Bitmap;

import com.sisyphean.practice.view.IView;

public interface IAuthView extends IView {

    void hideSubmit(int status);

    void showSubmit();

    void showBackImg(Bitmap backImg);

    void showJustImg(Bitmap justImg);

    int getGroupHeight();

    int getGroupWidth();

    String getTrueName();

    String getIDCrad();

    void setTrueName(String trueName);

    void setIDCard(String idCardNum);
}
