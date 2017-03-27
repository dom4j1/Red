package com.dom.red.ui.main.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.app.App;
import com.dom.red.base.SimpleActivity;
import com.dom.red.ui.gank.activity.FuliActivity;
import com.dom.red.ui.gank.activity.HomeActivity;
import com.dom.red.ui.main.adapter.MainAdapter;
import com.dom.red.ui.zhihu.activity.AboutRedActivity;
import com.dom.red.ui.zhihu.activity.ThemeActivity;
import com.dom.red.ui.zhihu.fragment.ZhihuDaily;
import com.dom.red.ui.zhihu.fragment.ZhihuHot;
import com.dom.red.ui.zhihu.fragment.ZhihuSection;
import com.dom.red.util.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends SimpleActivity
        implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tb_home)
    Toolbar mToolBar;
    @BindView(R.id.nv_home)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout_home)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tab_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    private List<Fragment> mList;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initEventAndData() {
        mToolBar.setTitle(R.string.home);
        setSupportActionBar(mToolBar);
        //改变侧滑栏条目得颜色
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.seleter);
        mNavigationView.setItemTextColor(csl);
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        mToolBar.setOnMenuItemClickListener(this);
        mList = new ArrayList<>();
        mList.add(new ZhihuDaily());
        mList.add(new ZhihuSection());
        mList.add(new ZhihuHot());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.setDataList(mList);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.classify:
//                goToTopicSection();
//                break;
//            case R.id.about:
//                gotToAboutRed();
//                break;
        }
        return true;
    }

    private void gotToAboutRed() {
        Intent intent = new Intent(App.getInstance(), AboutRedActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void goToTopicSection() {
        Intent intent = new Intent(App.getInstance(), ThemeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme_daily:
                goToTopicSection(); //主题章节
                break;
            case R.id.about_red:  //关于
                gotToAboutRed();
                break;
            case R.id.gank: //gank
                gotToGank();
                break;
            case R.id.fuli: //福利
                goToFuli();
                break;
            case R.id.left_share:
                shareApp(); //视频
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    private void shareApp() {
        ShareUtil.shareApp(this);
    }

    private void goToFuli() {
        Intent intent = new Intent(this, FuliActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void gotToGank() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitDialog();
        }
        return true;
    }

    private void showExitDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定退出 Red?")
                .setCancelable(true)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((App) getApplication()).exitApp();
                    }
                })
                .create();
        dialog.show();

    }
}
