package com.dom.red.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.webkit.WebView;

import com.dom.red.ui.activity.WebViewActivity;
import com.dom.red.util.Final;

/**
 * Created by dom4j on 2017/3/23.
 */

public class TextViewSpan extends ClickableSpan {

    private final String url;
    private Context mContext;
    public TextViewSpan(Context context,String str){
        this.mContext = context;
        this.url = str;
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.BLUE);
    }

    @Override
    public void onClick(View widget) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(Final.URL,url);
        mContext.startActivity(intent);
    }
}
