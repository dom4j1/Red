package com.dom.red.ui.zhihu.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.zhihu.ThemeListBean;
import com.dom.red.presenter.ThemePresenter;
import com.dom.red.presenter.contract.ThemeContract;
import com.dom.red.ui.main.adapter.ThemeDownVp;
import com.dom.red.ui.main.adapter.ThemeTopViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemeActivity extends BaseActivity<ThemePresenter> implements ThemeContract.View,ViewPager.OnPageChangeListener {

    @BindView(R.id.fh_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_top_vp)
    ViewPager mTopVp;
    @BindView(R.id.fl_tl)
    TabLayout mTabLayout;
    @BindView(R.id.fl_bm_vp)
    ViewPager mDownVp;
    @BindView(R.id.theme_ctl)
    CollapsingToolbarLayout mCtl;

    private List<ThemeListBean.OthersBean> mOthers;

    @Override
    protected int getLayout() {
        return R.layout.avtivity_theme;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "主题日报");
        mDownVp.setOffscreenPageLimit(3);
        mCtl.setExpandedTitleColor(Color.WHITE);
        mCtl.setCollapsedTitleTextColor(Color.WHITE);
        mDownVp.addOnPageChangeListener(this);
        mTopVp.addOnPageChangeListener(this);
        mPresenter.getThemeList();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showThemeList(ThemeListBean themeListBean) {
        List<ThemeListBean.OthersBean> others = themeListBean.getOthers();
        this.mOthers = others.subList(8,others.size());
        mPresenter.createTopAdapter(mOthers);
        mPresenter.createDownAdapter(mOthers);
    }

    @Override
    public void showTopViewPager(ThemeTopViewPager adapter) {
        mTopVp.setAdapter(adapter);
    }

    @Override
    public void showDownViewPager(ThemeDownVp tdv) {
        mDownVp.setAdapter(tdv);
        mTabLayout.setupWithViewPager(mDownVp);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTopVp.setCurrentItem(position);
        mDownVp.setCurrentItem(position);
        mCtl.setTitle(mOthers.get(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
