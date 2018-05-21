package com.sisyphean.practice.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {

    private ObjectAnimator hideAnimator, showAnimator;

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d("nestedscroll", "view = " + child.toString() + " | dependency = " + dependency.toString());
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("nestedscroll", "dependency hight = " + dependency.getY());
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d("nestedscroll", "axes = " + axes);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d("nestedscroll", "dx = " + dx);
        if (dy < 0) {
            //向下滑动 显示BottomNavigationView
            if (showAnimator == null) {
                showAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0);
                showAnimator.setDuration(200);
            }

            if (!showAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
                showAnimator.start();
            }

        } else if (dy > 0) {
            //向上滑动 隐藏BottomNavigationView
            if (hideAnimator == null) {
                hideAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight());
                hideAnimator.setDuration(200);
            }

            if (!hideAnimator.isRunning() && child.getTranslationY() <= 0) {
                hideAnimator.start();
            }
        }
    }
}
