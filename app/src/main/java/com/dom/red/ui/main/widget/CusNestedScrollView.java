package com.dom.red.ui.main.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by dom4j on 2017/3/10.
 */

public class CusNestedScrollView extends NestedScrollView{
    ScrollInterface web;

    public CusNestedScrollView(Context context) {
        super(context);
    }

    public CusNestedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CusNestedScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        web.onSChanged(l, t, oldl, oldt);
    }

    public void setOnCusScroolChangeListener(ScrollInterface t) {
        this.web = t;
    }

    /**
     * 定义滑动接口
     * @param
     */
    public interface ScrollInterface {
        public void onSChanged(int l, int t, int oldl, int oldt);
    }
}
