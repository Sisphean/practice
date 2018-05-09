package com.sisyphean.practice.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SwipeBackLayout extends FrameLayout {
    private final String TAG = getClass().getSimpleName();
    private ViewDragHelper mViewDragHelper;
    private int oldLeft = 0;

    public SwipeBackLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                Log.d(TAG, dx + "");
                oldLeft = left;
                int leftEdge = 0;
                int rightEdge = getWidth();
                return Math.min(rightEdge, Math.max(leftEdge, left));
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                if (oldLeft < (getWidth() / 2))
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                else
                    mViewDragHelper.settleCapturedViewAt(getWidth(), 0);
                invalidate();
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "getChildCount = " + getChildCount());
    }

    public void attachActivity(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View decorChild = decorView.getChildAt(0);
        Log.d(TAG, decorChild.toString());
        decorView.removeView(decorChild);
        addView(decorChild);
        decorView.addView(this);

    }
}
