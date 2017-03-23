package com.dom.red.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.dom.red.ui.Adapter.CommonAdapter;
import com.dom.red.ui.Adapter.ViewHolder;
import com.dom.red.ui.widget.TextViewSpan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/23.
 */

public class AboutRedActivity extends SimpleActivity {
    @BindView(R.id.toolBar1)
    Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mColl;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn)
    FloatingActionButton mFAB;

    private int index = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initEventAndData() {
        String appName = (String) getResources().getText(R.string.app_name);
        setToolBar(mToolbar,"");
        mColl.setTitle(appName);
        mColl.setExpandedTitleColor(Color.WHITE);
        mColl.setCollapsedTitleTextColor(Color.WHITE);

        List<String> list = new ArrayList<>();
        final List<String> colorList = new ArrayList<>();

        String str1 = (String) getResources().getText(R.string.s1);
        String str2 = (String) getResources().getText(R.string.s2);
        String str3 = (String) getResources().getText(R.string.s3);
        String str4 = (String) getResources().getText(R.string.s4);
        String str5 = (String) getResources().getText(R.string.s5);
        String str6 = (String) getResources().getText(R.string.s6);
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        list.add(str6);

        String c1 = (String) getResources().getText(R.string.c1);
        String c2 = (String) getResources().getText(R.string.c2);
        String c3 = (String) getResources().getText(R.string.c3);
        String c4 = (String) getResources().getText(R.string.c4);
        String c5 = (String) getResources().getText(R.string.c5);
        colorList.add(c1);
        colorList.add(c2);
        colorList.add(c3);
        colorList.add(c4);
        colorList.add(c5);



        CommonAdapter adapter = new CommonAdapter<String>(this,R.layout.item_theme,list) {
            @Override
            public void conver(ViewHolder viewHodler, String str) {
                TextView textView = viewHodler.getView(R.id.item_theme_tv);
                textView.setText(str);
                if(index < 2){
                SpannableStringBuilder spannable = new SpannableStringBuilder(str);
                int startIndex = str.indexOf(colorList.get(index));
                int endIndex = startIndex + colorList.get(index).length();
                spannable.setSpan(new TextViewSpan(AboutRedActivity.this,colorList.get(index)),startIndex,endIndex
                        , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setText(spannable);
                index++;
                }
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

    }

}
