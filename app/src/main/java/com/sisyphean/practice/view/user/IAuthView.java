package com.sisyphean.practice.view.user;

import android.graphics.Bitmap;

import com.sisyphean.practice.view.IView;

public interface IAuthView extends IView {

    void hideSubmit();

    void showSubmit();

    void showBackImg(Bitmap backImg);

    void showJustImg(Bitmap justImg);
}