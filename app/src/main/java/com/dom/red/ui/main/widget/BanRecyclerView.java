package com.dom.red.ui.main.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dom4j on 2017/3/14.
 */

public class BanRecyclerView extends RecyclerView{
    public BanRecyclerView(Context context) {
        super(context);
    }

    public BanRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BanRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
