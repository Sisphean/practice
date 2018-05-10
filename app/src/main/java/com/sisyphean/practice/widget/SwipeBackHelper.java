package com.sisyphean.practice.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import com.sisyphean.practice.R;

public class SwipeBackHelper {

    private Activity mActivity;
    private SwipeBackLayout swipeBackLayout;

    public SwipeBackHelper(Activity activity) {
        mActivity = activity;
    }


    public void onActivityCreate() {
        mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mActivity.getWindow().getDecorView().setBackgroundDrawable(null);
        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(mActivity).inflate(R.layout.layout_swipeback, null);
        swipeBackLayout.setOnFinishScroll(new SwipeBackLayout.OnFinishScroll() {
            @Override
            public void complete() {
                mActivity.finish();
            }
        });
    }

    public void onPostCreate() {
        swipeBackLayout.attachActivity(mActivity);
    }
}
