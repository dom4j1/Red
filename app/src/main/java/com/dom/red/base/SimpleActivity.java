package com.dom.red.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dom.red.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by dom4j on 2017/3/7.
 * 无MVP的Activity的基类
 */

public abstract class SimpleActivity extends SupportActivity{

    protected Activity mContext;
    protected Unbinder mUnBinder;
    protected FragmentManager mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        App.getInstance().addActivity(this);
        mFragmentManager = getSupportFragmentManager();
        initEventAndData();
    }

    public void setToolBar(Toolbar toolBar, String title){
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public FragmentTransaction fragmentReplace(int layoutID, Fragment fragment){
       return mFragmentManager.beginTransaction().replace(layoutID,fragment);
    }

    public FragmentTransaction fragmentAdd(int layoutID, Fragment fragment){
        return mFragmentManager.beginTransaction().add(layoutID,fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }
    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
