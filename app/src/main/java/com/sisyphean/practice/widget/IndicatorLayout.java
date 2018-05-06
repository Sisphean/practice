package com.sisyphean.practice.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sisyphean.practice.R;

public class IndicatorLayout extends LinearLayout {

    private int mCurPosition;

    private int count;

    private Context context;

    public IndicatorLayout(Context context) {
        this(context, null, 0);
    }

    public IndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(HORIZONTAL);
    }


    public void setCurPosition(int position) {
        removeAllViews();
        mCurPosition = position;
        draw();
    }

    public void setCount(int count) {
        this.count = count;
        mCurPosition = 0;
        draw();
    }

    private void draw() {
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setPadding(7, 0, 7, 0);
            if (i == mCurPosition) {
                imageView.setImageResource(R.drawable.pic_indicator_cur);
            } else {
                imageView.setImageResource(R.drawable.pic_indicator);
            }

            addView(imageView);
        }
    }

}
