package com.sisyphean.practice.widget;

import android.app.Activity;
import android.view.LayoutInflater;

import com.sisyphean.practice.R;

public class SwipeBackHelper {

    private Activity mActivity;
    private SwipeBackLayout swipeBackLayout;

    public SwipeBackHelper(Activity activity) {
        mActivity = activity;
    }


    public void onActivityCreate() {
        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(mActivity).inflate(R.layout.layout_swipeback, null);
    }

    public void onPostCreate() {
        swipeBackLayout.attachActivity(mActivity);
    }
}
