package com.dom.red.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dom.red.R;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.zhihu.ThemeList;
import com.dom.red.presenter.ThemeFragmentPresenter;
import com.dom.red.presenter.contract.ThemeFragmentContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.MulitItemTypeSupport;
import com.dom.red.ui.main.adapter.MulitItemTypeaAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.zhihu.activity.ContentActivity;
import com.dom.red.util.Constants;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemeFragment extends BaseFragment<ThemeFragmentPresenter> implements ThemeFragmentContract.View,ViewHolder.OnItemClickLisenter,MulitItemTypeSupport<ThemeList.StoriesBean> {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    private int id;
    private CommonAdapter mAdapter;
    private List<ThemeList.StoriesBean> mStories;

    private final int FLAG1 = 1;
    private final int FLAG2 = 2;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected void initEventAndData() {
        mAdapter = new MulitItemTypeaAdapter<ThemeList.StoriesBean>(mContext,null,this) {

            @Override
            public void conver(ViewHolder viewHodler, ThemeList.StoriesBean data) {
                int itemType = viewHodler.getItemViewType();
                 if(itemType == FLAG1){
                     if(data.isFlag()){
                         viewHodler.setTextColor(R.id.item_theme_tv, Color.GRAY);
                     }else{
                         viewHodler.setTextColor(R.id.item_theme_tv, Color.BLACK);
                     }
                     viewHodler.setText(R.id.item_theme_tv,data.getTitle());
                 }else if(itemType == FLAG2){
                     if(data.isFlag()){
                         viewHodler.setTextColor(R.id.tv_daily_item_title, Color.GRAY);
                     }else{
                         viewHodler.setTextColor(R.id.tv_daily_item_title, Color.BLACK);
                     }
                     viewHodler.setText(R.id.tv_daily_item_title,data.getTitle())
                     .loadImageGlide(R.id.iv_daily_item_image,data.getImages().get(0));
                 }
                viewHodler.setOnItemClickLisenter(ThemeFragment.this,R.id.iv_daily_item_image);
            }

        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getThemeList(id);
    }

    @Override
    public void showError(String msg) {

    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public void showThemeList(ThemeList themeList) {
        mStories = themeList.getStories();
        mAdapter.setDataList(mStories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void itemClick(View itemView, int position) {
        mStories.get(position).setFlag(true);
        mAdapter.notifyDataSetChanged();
        Intent intent = new Intent(mContext, ContentActivity.class);
        intent.putExtra(Constants.ID,mStories.get(position).getId());
        if(itemView != null){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity,itemView,"shareView").toBundle());
        }else{
            startActivity(intent);
            mActivity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    @Override
    public int getLayoutID(int ItemType) {
        if(ItemType == FLAG1){
            return R.layout.item_theme;
        }else{
            return R.layout.item_sectionchild;
        }
    }

    @Override
    public int getItemViewType(int position, ThemeList.StoriesBean bean) {
        if(bean.getImages() == null){
            return FLAG1;
        }else{
            return FLAG2;
        }
    }


}
