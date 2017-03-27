package com.dom.red.ui.gank.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.presenter.FuliPresenter;
import com.dom.red.presenter.contract.FuliContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.RefreshRecyclerView;
import com.dom.red.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/24.
 */

public class FuliActivity extends BaseActivity<FuliPresenter> implements
        FuliContract.View, ViewHolder.OnItemClickLisenter,SwipeRefreshLayout.OnRefreshListener {

    private static final String FULI = "福利";
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.fuli_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fuli_refreshView)
    SwipeRefreshLayout mSwipRefreshView;

    private int num = 20;
    private int page = 1;
    private GridLayoutManager mLayoutManager;
    private CommonAdapter mAdapter;

    private List<MeiziBean> mList;
    private boolean isLoadMore = false;

    private int lastCount;

    @Override
    protected int getLayout() {
        return R.layout.activity_fuli;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "妹子");
        mList = new ArrayList<>();
        mAdapter = new CommonAdapter<MeiziBean>(this, R.layout.item_fuli, null) {
            @Override
            public void conver(ViewHolder viewHodler, MeiziBean data) {
                viewHodler.loadImageGlide(R.id.iv_fuli, data.getUrl());
                viewHodler.setOnItemClickLisenter(FuliActivity.this,R.id.iv_fuli);
            }
        };
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipRefreshView.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new ScrollListener());
        mPresenter.getMeiziList(FULI, num, page);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showMeiziList(List<MeiziBean> list) {
        if (mSwipRefreshView.isRefreshing())
            mSwipRefreshView.setRefreshing(false);
        mList.addAll(list);
        mAdapter.setDataList(mList);
        if (!isLoadMore) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDataList(mList);
        }
    }

    @Override
    public void itemClick(View startView, int position) {
        String url = mList.get(position).getUrl();
        Intent intent = new Intent();
        intent.setClass(this,MeiZiActivity.class);
        intent.putExtra(Constants.URL,url);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,startView,"shareView").toBundle());
    }

    public void loadMore() {
        isLoadMore = true;
        page++;
        mPresenter.getMeiziList(FULI, num, page);
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        page++;
        mList.clear();
        mPresenter.getMeiziList(FULI, num, page);
    }


    public class ScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) {
                int lastPosition = mLayoutManager.findLastVisibleItemPosition();
                int count = mLayoutManager.getItemCount();
                if (lastPosition + 1 == count && lastPosition + 2 != lastCount) {
                    lastCount = count;
                    loadMore();
                }
            }
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
