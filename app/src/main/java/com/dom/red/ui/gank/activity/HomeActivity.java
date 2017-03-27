package com.dom.red.ui.gank.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.presenter.HomePresenter;
import com.dom.red.presenter.contract.HomeContract;
import com.dom.red.ui.main.adapter.HomeViewPager;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/24.
 */

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.gank_home_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tl_gank_home)
    TabLayout mTabLayout;
    @BindView(R.id.vp_gank_home)
    ViewPager mViewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar,"Gank");
        mViewPager.setOffscreenPageLimit(3);
        mPresenter.createHomeList();
    }

    @Override
    public void showHomeList(HomeViewPager adapter) {
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void showError(String msg) {

    }
}
