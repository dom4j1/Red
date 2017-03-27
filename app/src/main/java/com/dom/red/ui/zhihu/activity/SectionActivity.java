package com.dom.red.ui.zhihu.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.zhihu.SectionChildListBean;
import com.dom.red.presenter.SectionChildPresenter;
import com.dom.red.presenter.contract.SectionChildContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.RefreshRecyclerView;
import com.dom.red.util.Constants;
import com.dom.red.util.SnakerbarUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/26.
 */

public class SectionActivity extends BaseActivity<SectionChildPresenter>
        implements SectionChildContract.View, RefreshRecyclerView.OnRefresh2Lisenter, ViewHolder.OnItemClickLisenter {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.section_refreshView_child)
    RefreshRecyclerView mRecyclerView;
    @BindView(R.id.sectionChild_parent)
    LinearLayout mParent;
    private CommonAdapter mAdapter;
    private int mID;
    private List<SectionChildListBean.StoriesBean> mStories;

    @Override
    protected int getLayout() {
        return R.layout.activity_section;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mID = intent.getIntExtra(Constants.ID, 0);
        String title = intent.getStringExtra(Constants.TITLE);
        setToolBar(mToolbar, title);
        mAdapter = new CommonAdapter<SectionChildListBean.StoriesBean>
                (mContext, R.layout.item_sectionchild, null) {
            @Override
            public void conver(ViewHolder viewHodler, SectionChildListBean.StoriesBean data) {
                viewHodler.loadImageGlide(R.id.iv_daily_item_image, data.getImages().get(0))
                        .setText(R.id.tv_daily_item_title, data.getTitle());
                viewHodler.setOnItemClickLisenter(SectionActivity.this,R.id.iv_daily_item_image);
            }
        };
        mRecyclerView.setOnRefreshLisenter(this,false);
        mPresenter.getSectionChildList(mID);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showSectionChildList(SectionChildListBean list) {
        if (mRecyclerView.getSwipeRefreshLayout().isRefreshing())
            mRecyclerView.getSwipeRefreshLayout().setRefreshing(false);
        mStories = list.getStories();
        mAdapter.setDataList(mStories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadMore() {
        SnakerbarUtil.showShort(mParent,getResources().getString(R.string.noData));
    }

    @Override
    public void refresh() {
        mPresenter.getSectionChildList(mID);
    }

    @Override
    public void itemClick(View startView, int position) {
        int id = mStories.get(position).getId();
        Intent intent = new Intent();
        intent.setClass(this,ContentActivity.class);
        intent.putExtra(Constants.ID,id);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,startView,"shareView").toBundle());
    }

}
