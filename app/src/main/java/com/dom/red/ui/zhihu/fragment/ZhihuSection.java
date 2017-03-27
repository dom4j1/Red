package com.dom.red.ui.zhihu.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.dom.red.R;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.zhihu.SectionBean;
import com.dom.red.presenter.SectionPresenter;
import com.dom.red.presenter.contract.ZhiSectionContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.RefreshRecyclerView;
import com.dom.red.ui.zhihu.activity.SectionActivity;
import com.dom.red.util.Constants;
import com.dom.red.util.SnakerbarUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/25.
 */

public class ZhihuSection extends BaseFragment<SectionPresenter> implements ZhiSectionContract.View,ViewHolder.OnItemClickLisenter,RefreshRecyclerView.OnRefresh2Lisenter {
    @BindView(R.id.section_refreshView)
    RefreshRecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private List<SectionBean.DataBean> mData;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected void initEventAndData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mRecyclerView.setOnRefreshLisenter(this,false);
        mAdapter = new CommonAdapter<SectionBean.DataBean>(mContext, R.layout.item_section,null) {
            @Override
            public void conver(ViewHolder viewHodler, SectionBean.DataBean data) {
                viewHodler.setText(R.id.section_des,data.getDescription())
                        .setText(R.id.section_name,data.getName())
                        .loadImageGlideScroll(R.id.section_bg,data.getThumbnail());
                viewHodler.setOnItemClickLisenter(ZhihuSection.this);
            }
        };
        mPresenter.getSectionList();
    }

    @Override
    public void showSectionList(SectionBean list) {
        if(mRecyclerView.getSwipeRefreshLayout().isRefreshing())
            mRecyclerView.getSwipeRefreshLayout().setRefreshing(false);
        mData = list.getData();
        mAdapter.setDataList(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void itemClick(View startView, int position) {
        int id = mData.get(position).getId();
        String title = mData.get(position).getName();
        Intent intent = new Intent(mContext, SectionActivity.class);
        intent.putExtra(Constants.ID,id);
        intent.putExtra(Constants.TITLE,title);
        startActivity(intent);
    }

    @Override
    public void loadMore() {
        SnakerbarUtil.showShort(mRecyclerView,getResources().getString(R.string.noData));
    }

    @Override
    public void refresh() {
        mPresenter.getSectionList();
    }
}
