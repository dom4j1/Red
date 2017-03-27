package com.dom.red.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dom.red.R;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.zhihu.HotListBean;
import com.dom.red.presenter.HotPresenter;
import com.dom.red.presenter.contract.ZhiHotContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.RefreshRecyclerView;
import com.dom.red.ui.zhihu.activity.ContentActivity;
import com.dom.red.util.Constants;
import com.dom.red.util.SnakerbarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/25.
 */

public class ZhihuHot extends BaseFragment<HotPresenter> implements ZhiHotContract.View, ViewHolder.OnItemClickLisenter, RefreshRecyclerView.OnRefresh2Lisenter {
    @BindView(R.id.hot_refreshView)
    RefreshRecyclerView mRecyclerView;
    @BindView(R.id.hot_parent)
    LinearLayout mParentView;
    private CommonAdapter mAdapter;
    private List<HotListBean.RecentBean> mRecent;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initEventAndData() {
        mAdapter = new CommonAdapter<HotListBean.RecentBean>(mContext, R.layout.item_sectionchild, null) {
            @Override
            public void conver(ViewHolder viewHodler, HotListBean.RecentBean data) {
                viewHodler.loadImageGlide(R.id.iv_daily_item_image, data.getThumbnail())
                        .setText(R.id.tv_daily_item_title, data.getTitle());
                viewHodler.setOnItemClickLisenter(ZhihuHot.this, R.id.iv_daily_item_image);
            }
        };
        mRecyclerView.setOnRefreshLisenter(this,false);
        mPresenter.getHotListData();
    }

    @Override
    public void showHotListData(HotListBean data) {
        if (mRecyclerView.getSwipeRefreshLayout().isRefreshing())
            mRecyclerView.getSwipeRefreshLayout().setRefreshing(false);
        mRecent = data.getRecent();
        mAdapter.setDataList(mRecent);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void itemClick(View startView, int position) {
        int id = mRecent.get(position).getNews_id();
        Intent intent = new Intent();
        intent.setClass(mContext, ContentActivity.class);
        intent.putExtra(Constants.ID, id);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, startView, "shareView").toBundle());
    }

    @Override
    public void loadMore() {
        SnakerbarUtil.showShort(mParentView,getResources().getString(R.string.noData));
    }

    @Override
    public void refresh() {
        mPresenter.getHotListData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
