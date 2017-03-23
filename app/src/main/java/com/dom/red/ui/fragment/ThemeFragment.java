package com.dom.red.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dom.red.R;
import com.dom.red.base.BaseFragment;
import com.dom.red.model.bean.ThemeList;
import com.dom.red.presenter.ThemeFragmentPresenter;
import com.dom.red.presenter.contract.ThemeFragmentContract;
import com.dom.red.ui.Adapter.CommonAdapter;
import com.dom.red.ui.Adapter.MulitItemTypeSupport;
import com.dom.red.ui.Adapter.MulitItemTypeaAdapter;
import com.dom.red.ui.Adapter.ViewHolder;
import com.dom.red.ui.activity.ContentActivity;
import com.dom.red.ui.activity.MainActivity;
import com.dom.red.util.Final;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemeFragment extends BaseFragment<ThemeFragmentPresenter> implements ThemeFragmentContract.View,ViewHolder.OnItemClickLisenter,MulitItemTypeSupport<ThemeList.StoriesBean> {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.theme_sdv)
    SimpleDraweeView mSdv;

    private int id;
    private CommonAdapter mAdapter;
    private List<ThemeList.StoriesBean> mStories;

    private final int FLAG1 = 1;
    private final int FLAG2 = 2;

    public ThemeFragment(int id) {
        this.id = id;
    }

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
                         viewHodler.setTextColor(R.id.item_tv, Color.GRAY);
                     }else{
                         viewHodler.setTextColor(R.id.item_tv, Color.BLACK);
                     }
                     viewHodler.setText(R.id.item_tv,data.getTitle())
                     .loadImage(R.id.item_sdv,data.getImages().get(0));
                 }
                viewHodler.setOnItemClickLisenter(ThemeFragment.this);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getThemeList(id);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showThemeList(ThemeList themeList) {
        List<ThemeList.EditorsBean> editors = themeList.getEditors();
        mStories = themeList.getStories();
        if(editors.size() > 0){
            mSdv.setImageURI(editors.get(0).getAvatar());
        }
        mAdapter.setDataList(mStories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void itemClick(View itemView, int position) {
        mStories.get(position).setFlag(true);
        mAdapter.notifyDataSetChanged();
        Intent intent = new Intent(mContext, ContentActivity.class);
        intent.putExtra(Final.ID,mStories.get(position).getId());
        if(mStories.get(position).getImages() == null){
            intent.putExtra(Final.ISIMAGE,true);
        }
        startActivity(intent);
    }

    @Override
    public int getLayoutID(int ItemType) {
        if(ItemType == FLAG1){
            return R.layout.item_theme;
        }else{
            return R.layout.item_home;
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
