package com.dom.red.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.zhihu.HomeListBean;
import com.dom.red.presenter.DailyPresenter;
import com.dom.red.presenter.contract.ZhiDailyContract;
import com.dom.red.ui.main.activity.TimeActivity;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.GlideImageLoader;
import com.dom.red.ui.zhihu.activity.ContentActivity;
import com.dom.red.util.CircularAnimUtil;
import com.dom.red.util.Constants;
import com.dom.red.util.SnakerbarUtil;
import com.dom.red.util.TimeUtil;
import com.github.ybq.android.spinkit.SpinKitView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/25.
 */

public class ZhihuDaily extends BaseFragment<DailyPresenter> implements ZhiDailyContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, ViewHolder.OnItemClickLisenter {

    @BindView(R.id.item_daily_banner)
    Banner mBanner;
    @BindView(R.id.item_daily_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.item_daily_swiprefresh)
    SwipeRefreshLayout mSwipRefresh;
    @BindView(R.id.item_deaily_scollView)
    NestedScrollView mScrollView;
    @BindView(R.id.fab_calender)
    FloatingActionButton mFab;
    @BindView(R.id.item_daily_time)
    TextView mTime;
    @BindView(R.id.item_daily_CoordinatorLayout)
    CoordinatorLayout mParentView;
    @BindView(R.id.spin_kit)
    SpinKitView mSpinkit;

    private CommonAdapter mAdapter;

    private List<String> mUrls;
    private List<String> mTitles;
    private LinearLayoutManager mLinearLayoutManager;
    private List<HomeListBean.StoriesBean> mStories;
    private String mDate;

    private static ZhihuDaily instance;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().register(this);
        instance = this;
        mUrls = new ArrayList<>();
        mTitles = new ArrayList<>();
        mRecyclerView.setNestedScrollingEnabled(false);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mAdapter = new CommonAdapter<HomeListBean.StoriesBean>(mContext, R.layout.item_home, null) {
            @Override
            public void conver(ViewHolder viewHodler, HomeListBean.StoriesBean bean) {
                if (bean.isFlag()) {
                    viewHodler.setTextColor(R.id.item_home_tv, Color.GRAY);
                } else {
                    viewHodler.setTextColor(R.id.item_home_tv, Color.BLACK);
                }
                viewHodler.setText(R.id.item_home_tv, bean.getTitle())
                        .loadImageFresco(R.id.item_home_sdv, bean.getImages().get(0));
                viewHodler.setOnItemClickLisenter(ZhihuDaily.this);
            }
        };
        mFab.setOnClickListener(this);
        mSwipRefresh.setOnRefreshListener(this);
        mScrollView.setOnScrollChangeListener(new OnScrllLisenter());
        mPresenter.getDailyList();
    }

    @Override
    public void showDailyList(HomeListBean data) {
        if (mSwipRefresh.isRefreshing()) {
            mSwipRefresh.setRefreshing(false);
        }
        mBanner.setVisibility(View.VISIBLE);
        mTitles.clear();
        mUrls.clear();
        mTime.setText("今日热闻");
        mStories = data.getStories();
        List<HomeListBean.TopStoriesBean> top_stories = data.getTop_stories();
        for (HomeListBean.TopStoriesBean bean : top_stories) {
            mUrls.add(bean.getImage());
            mTitles.add(bean.getTitle());
        }
        mAdapter.setDataList(mStories);
        mBanner.setImages(mUrls);
        mBanner.setBannerTitles(mTitles);
        mBanner.start();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showBeforeList(HomeListBean data) {
        mTime.setText(TimeUtil.splitTime(data.getDate()));
        mStories = data.getStories();
        mAdapter.setDataList(mStories);
        mRecyclerView.setAdapter(mAdapter);
        mSpinkit.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getDailyList();
    }


    @Override
    public void onClick(View v) {
        Intent it = new Intent();
        it.setClass(mContext, TimeActivity.class);
        CircularAnimUtil.startActivity(mActivity, it, mFab, R.color.fab_bg);
    }

    public static ZhihuDaily getInstance(){
        return instance;
    }

    @Override
    public void itemClick(View shareView, int position) {
        int id = mStories.get(position).getId();
        Intent intent = new Intent();
        intent.setClass(mContext, ContentActivity.class);
        intent.putExtra(Constants.ID, id);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
        mContext.startActivity(intent, options.toBundle());
    }

    public class OnScrllLisenter implements NestedScrollView.OnScrollChangeListener {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            View view = mScrollView.getChildAt(mScrollView.getChildCount() - 1);
            int d = view.getBottom();
            d -= (mScrollView.getHeight() + mScrollView.getScrollY());
            if (d == 0) {
                showSnakerbar();
            }
        }
    }

    private void showSnakerbar() {
        SnakerbarUtil.showShort(mParentView, getResources().getString(R.string.noData));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String date) {
        mStories.clear();
        mBanner.setVisibility(View.GONE);
        mAdapter.setDataList(mStories);
        mSpinkit.setVisibility(View.VISIBLE);
        mPresenter.getBeforeList(date);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStopDialog(boolean date) {
        mSpinkit.setVisibility(View.GONE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
