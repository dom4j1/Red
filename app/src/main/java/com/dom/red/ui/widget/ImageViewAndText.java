package com.dom.red.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dom.red.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by dom4j on 2017/3/8.
 */

public class ImageViewAndText extends RelativeLayout {

    private SimpleDraweeView sdv;
    private TextView tv;

    public ImageViewAndText(Context context) {
        this(context,null);
    }

    public ImageViewAndText(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public ImageViewAndText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_imageatext,this);
        sdv = (SimpleDraweeView) view.findViewById(R.id.sdv_home_top);
        tv = (TextView) view.findViewById(R.id.tv_home_top);
    }

    public void setImageUrl(String url){
        sdv.setImageURI(url);
    }

    public void setText(String text){
        tv.setText(text);
    }
}
