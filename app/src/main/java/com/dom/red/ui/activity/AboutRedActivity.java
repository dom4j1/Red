package com.dom.red.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dom.red.R;
import com.dom.red.base.SimpleActivity;

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
        mColl.setCollapsedTitleTextColor(Color.RED);

        List<String> list = new ArrayList<>();

    }

}
