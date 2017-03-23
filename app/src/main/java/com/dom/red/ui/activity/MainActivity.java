package com.dom.red.ui.activity;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dom.red.R;
import com.dom.red.app.App;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.HomeListBean;
import com.dom.red.presenter.MainPresenter;
import com.dom.red.presenter.contract.MainContract;
import com.dom.red.ui.Adapter.CommonAdapter;
import com.dom.red.ui.Adapter.HeaderAndFooterSupport;
import com.dom.red.ui.Adapter.ViewHolder;
import com.dom.red.ui.widget.RefreshRecyclerView;
import com.dom.red.util.Final;
import com.dom.red.util.LogUtil;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View, Toolbar.OnMenuItemClickListener,RefreshRecyclerView.OnRefresh2Lisenter,
        ViewHolder.OnItemClickLisenter,NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tb_home)
    Toolbar mToolBar;
    @BindView(R.id.nv_home)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout_home)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.rl_home)
    RefreshRecyclerView mRecyclerHome;

    private CommonAdapter mAdapter;
    private HeaderAndFooterSupport mAfs;
    public ViewHolder mViewHolder;

    private List<HomeListBean.StoriesBean> mList;
    private HomeListBean mHomeList;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mToolBar.setTitle(R.string.home);
        setSupportActionBar(mToolBar);

        Resources resource = (Resources)getBaseContext().getResources();
        ColorStateList csl = (ColorStateList)resource.getColorStateList(R.color.seleter);
        mNavigationView.setItemTextColor(csl);

        mNavigationView.setNavigationItemSelectedListener(this);

        mList = new ArrayList<>();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);

        mToolBar.setOnMenuItemClickListener(this);
        mAdapter = new CommonAdapter<HomeListBean.StoriesBean>(this, R.layout.item_home,null) {

            @Override
            public void conver(ViewHolder viewHodler, HomeListBean.StoriesBean o) {
                mViewHolder = viewHodler;
                if(o.isFlag()){
                    viewHodler.setTextColor(R.id.item_tv,Color.GRAY);
                }else{
                    viewHodler.setTextColor(R.id.item_tv,Color.BLACK);
                }
                viewHodler.setText(R.id.item_tv,o.getTitle())
                        .loadImage(R.id.item_sdv,o.getImages().get(0));
                viewHodler.setOnItemClickLisenter(MainActivity.this);
            }
        };
        mAfs = new HeaderAndFooterSupport(mAdapter);
        mRecyclerHome.setOnRefreshLisenter(this);

        mPresenter.getHomeList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public void showHomeList(HomeListBean homeListBean) {
        this.mHomeList = homeListBean;
        mList.clear();
        mList.addAll(homeListBean.getStories());
        mAdapter.setDataList(mList);
        mPresenter.getTopViewPager(true);
    }

    @Override
    public void showTopViewPager(ViewPager viewPager) {
        mAfs.cleanView();
        mAfs.addHeaderView(viewPager);
        mAfs.addHeaderView(createTextView());
        mRecyclerHome.setAdapter(mAfs);
        mRecyclerHome.setRefreshIng(false);
    }

    @Override
    public void showBeforeList(HomeListBean homeListBean) {
        this.mHomeList = homeListBean;
        mList.addAll(homeListBean.getStories());
        mAdapter.setDataList(mList);
        mAfs.notifyDataSetChanged();
    }


    public TextView createTextView(){
        TextView tv = new TextView(this);
        tv.setText("今日新闻");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(47,50,0,50);
        tv.setLayoutParams(lp);
        return tv;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.classify:
                /**
                 * 分类
                 */
                goToTopicSection();
                break;
            case R.id.about:
                /**
                 * 关于
                 */
                gotToAboutRed();
                break;
        }
        return true;
    }

    private void gotToAboutRed() {
        Intent intent = new Intent(App.getInstance(), AboutRedActivity.class);
        startActivity(intent);
    }

    private void goToTopicSection() {
        Intent intent = new Intent(App.getInstance(), ThemeActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadMore() {
        mPresenter.getBeforeList(mHomeList.getDate());
    }

    @Override
    public void refresh() {
        mPresenter.getHomeList();
    }

    @Override
    public void itemClick(View itemView, int position) {
        mList.get(position-2).setFlag(true);
        mAfs.notifyDataSetChanged();
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(Final.ID,mList.get(position-2).getId());
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:
               startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.item_2:
                Toast.makeText(this,"当前暂无最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_3:
                startActivity(new Intent(this,AboutRedActivity.class));
                break;
            case R.id.item_4:
                Toast.makeText(this,"一介书生",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
