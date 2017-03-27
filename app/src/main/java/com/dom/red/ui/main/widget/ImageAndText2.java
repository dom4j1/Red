package com.dom.red.ui.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dom.red.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by dom4j on 2017/3/10.
 */

public class ImageAndText2 extends RelativeLayout {
    private ImageView sdv;
    private TextView tv;
    private TextView tv2;

    public ImageAndText2(Context context) {
        this(context,null);
    }

    public ImageAndText2(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public ImageAndText2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_imageatext2,this);
        sdv = (ImageView) view.findViewById(R.id.sdv_content_top);
        tv = (TextView) view.findViewById(R.id.tv_content_top);
        tv2 = (TextView) view.findViewById(R.id.tv_content_top_2);

    }

    public void setImageUrl(String url){
        Glide.with(getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(sdv);
    }

    public void setText(String text){
        tv.setText(text);
    }

    public void setText2(String text){
        tv2.setText(text);
    }
}
