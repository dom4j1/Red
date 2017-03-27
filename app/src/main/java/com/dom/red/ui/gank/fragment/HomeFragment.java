package com.dom.red.ui.gank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dom.red.R;
import com.dom.red.app.App;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.gank.ClassifyList;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.presenter.HomeFragmentPresenter;
import com.dom.red.presenter.contract.HomeFragmentContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.HeaderAndFooterSupport;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.RefreshRecyclerView;
import com.dom.red.ui.zhihu.activity.WebViewActivity;
import com.dom.red.util.Constants;
import com.dom.red.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/24.
 */

public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentContract.View, RefreshRecyclerView.OnRefresh2Lisenter, ViewHolder.OnItemClickLisenter {
    @BindView(R.id.rl_gank_home)
    RefreshRecyclerView mRecyclerView;

    private int num = 10;
    private int page = 1;
    private CommonAdapter mAdapter;

    private List<ClassifyList> mList;
    private String mType;

    private boolean isLoadMore = false;
    String[] response = new String[]{"Android","iOS","前端"};
    private HeaderAndFooterSupport mHeaderFooterAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        Bundle arguments = getArguments();
        mType = (String) arguments.get(Constants.URL);
        mList = new ArrayList<>();
        mAdapter = new CommonAdapter<ClassifyList>(mContext, R.layout.item_home_gank, null) {
            @Override
            public void conver(ViewHolder viewHodler, ClassifyList data) {
                if(mType.equals(response[0])){
                    viewHodler.setimageResouse(R.id.iv_tech_icon,R.mipmap.ic_android);
                }else if(mType.equals(response[1])){
                    viewHodler.setimageResouse(R.id.iv_tech_icon,R.mipmap.ic_ios);
                }else if(mType.equals(response[2])){
                    viewHodler.setimageResouse(R.id.iv_tech_icon,R.mipmap.ic_web);
                }
                viewHodler.setText(R.id.tv_tech_title,data.getDesc())
                        .setText(R.id.tv_tech_time, TimeUtil.formatDateTime(TimeUtil.subStandardTime(data.getPublishedAt()), true));
                viewHodler.setOnItemClickLisenter(HomeFragment.this);
            }
        };
        mHeaderFooterAdapter = new HeaderAndFooterSupport(mAdapter);
        mRecyclerView.setOnRefreshLisenter(this);
        mPresenter.getDataList(mType, num, page);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDataList(List<ClassifyList> list) {
        mList.addAll(list);
        mAdapter.setDataList(mList);
        if(!isLoadMore){
            mPresenter.getGirlList(response.length);
        }else {
            mHeaderFooterAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showGrilList(List<MeiziBean> data) {
        if(mRecyclerView.getSwipeRefreshLayout().isRefreshing())
            mRecyclerView.setRefreshIng(false);
        ImageView imageView = new ImageView(App.getInstance());
        imageView.setLayoutParams(new ViewGroup.LayoutParams
                (ViewPager.LayoutParams.MATCH_PARENT,(int)getResources().getDimension(R.dimen.y300)));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(mType.equals(response[0])){
            Glide.with(this).load(data.get(0).getUrl()).placeholder(R.mipmap.loading).error(R.mipmap.load_error).into(imageView);
        }else if(mType.equals(response[1])){
            Glide.with(this).load(data.get(1).getUrl()).placeholder(R.mipmap.loading).error(R.mipmap.load_error).into(imageView);
        }else if(mType.equals(response[2])){
            Glide.with(this).load(data.get(2).getUrl()).placeholder(R.mipmap.loading).error(R.mipmap.load_error).into(imageView);
        }
        mHeaderFooterAdapter.addHeaderView(imageView);
        if (!isLoadMore) {
            mRecyclerView.setAdapter(mHeaderFooterAdapter);
        }else{
            mHeaderFooterAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore() {
        page++;
        isLoadMore = true;
        mPresenter.getDataList(mType, num, page);
    }

    @Override
    public void refresh() {
        page++;
        isLoadMore = false;
        mList.clear();
        mHeaderFooterAdapter.cleanView();
        mPresenter.getDataList(mType, num, page);
    }

    @Override
    public void itemClick(View itemView, int position) {
        String url = mList.get(position).getUrl();
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(Constants.URL, url);
        startActivity(intent);
    }


}
