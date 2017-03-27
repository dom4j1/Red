package com.dom.red.ui.gank.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/27.
 */

public class WebVideoActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.video_webView)
    WebView mVdeoView;

    @Override
    protected int getLayout() {
        return R.layout.activity_webvideo;
    }

    @Override
    protected void initEventAndData() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

}
