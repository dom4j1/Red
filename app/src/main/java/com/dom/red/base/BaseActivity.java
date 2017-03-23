package com.dom.red.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dom.red.app.App;
import com.dom.red.di.component.ActivityComponent;
import com.dom.red.di.component.DaggerActivityComponent;
import com.dom.red.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dom4j on 2017/3/7.
 * MVP activity的基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{

    @Inject
    protected T mPresenter;

    protected Activity mContext;
    protected FragmentManager mFragmentManager;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //添加返回按钮
        getSupportActionBar().setDisplayShowHomeEnabled(true);   //是否显示左上角的图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 onBackPressed();
            }
        });
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public ActivityModule getActivityModule(){
        return new ActivityModule(this);
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
        if(mPresenter != null){
            mPresenter.detachView();
        }
        mUnBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    protected abstract int getLayout();
    protected abstract void initInject();
    protected abstract void initEventAndData();
}
