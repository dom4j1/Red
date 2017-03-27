package com.dom.red.ui.zhihu.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.TextViewSpan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
        String str1 = (String) getResources().getText(R.string.s1);
        String str2 = (String) getResources().getText(R.string.s2);
        String str3 = (String) getResources().getText(R.string.s3);
        String str4 = (String) getResources().getText(R.string.s4);
        String str5 = (String) getResources().getText(R.string.s5);
        String str6 = (String) getResources().getText(R.string.s6);
        String str7 = (String) getResources().getText(R.string.s7);
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        list.add(str6);
        list.add(str7);

        CommonAdapter adapter = new CommonAdapter<String>(this,R.layout.item_about,list) {
            @Override
            public void conver(ViewHolder viewHodler, String str) {
                TextView textView = viewHodler.getView(R.id.about_tv);
                textView.setText(str);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

    }

}
