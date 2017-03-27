package com.dom.red.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.zhihu.DetailBean;
import com.dom.red.model.bean.zhihu.ExtraInfo;
import com.dom.red.presenter.ContentPresenter;
import com.dom.red.presenter.contract.ContentContract;
import com.dom.red.ui.main.widget.ImageAndText2;
import com.dom.red.util.Constants;
import com.dom.red.util.HtmlUtil;
import com.dom.red.util.NewWorkUtil;
import com.dom.red.util.ShareUtil;
import com.dom.red.util.ToastUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/10.
 */

public class ContentActivity extends BaseActivity<ContentPresenter> implements ContentContract.View, View.OnClickListener {

    @BindView(R.id.tb_content)
    Toolbar mToolbar;
    @BindView(R.id.wv_content)
    WebView mWebView;
    @BindView(R.id.sdv_content)
    ImageAndText2 mImage;
    @BindView(R.id.rl_content)
    NestedScrollView mScrollView;
    @BindView(R.id.ctl_content)
    CollapsingToolbarLayout ctlContent;

    private TextView mLike;
    private TextView mMsg;
    private int popularity;
    private int mId;
    private int comment;
    private boolean isLike = true;

    @Override
    protected int getLayout() {
        return R.layout.activity_content;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "");

        Intent intent = getIntent();
        mId = intent.getIntExtra(Constants.ID, 0);
//        boolean flag = intent.getBooleanExtra(Constants.ISIMAGE,false);
//        if(flag){
//            mImage.setVisibility(View.GONE);
//        }else{
//            mImage.setVisibility(View.VISIBLE);
//        }

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBlockNetworkImage(false); //允许WebView从网络上加载图片
        webSetting.setAppCacheEnabled(true); //启用应用缓存
        webSetting.setDomStorageEnabled(true); //启动DOM缓存
        webSetting.setDatabaseEnabled(true); //启动数据库缓存
        /**
         * 如果有网就使用默认的缓存模式  如果没有网络 只从缓存加载数据
         */
        if (NewWorkUtil.isNetworkConnected()) {
            webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSetting.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
        webSetting.setJavaScriptEnabled(true); //设置WebView可以运行JavaScript
        webSetting.setLoadWithOverviewMode(true); //加载内容充满全屏
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 指定WebView页面布局形式 调用该方法会引起页面重绘
        webSetting.setSupportZoom(true); //设置支持缩放
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mPresenter.getContent(mId);
        mPresenter.getNewsInfo(mId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_top, menu);
        View view1 = menu.findItem(R.id.content_like).getActionView();
        mLike = (TextView) view1.findViewById(R.id.tv_like_menu);
        View view2 = menu.findItem(R.id.content_content).getActionView();
        mMsg = (TextView) view2.findViewById(R.id.tv_msg_menu);
        mLike.setOnClickListener(this);
        mMsg.setOnClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showContent(DetailBean detailBean) {
        mImage.setImageUrl(detailBean.getImage());
        mImage.setText(detailBean.getTitle());
        mImage.setText2(detailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(detailBean.getBody(), detailBean.getCss(), detailBean.getJs());
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void showNewsInfo(ExtraInfo extraInfo) {
        popularity = extraInfo.getPopularity();
        comment = extraInfo.getComments();
        if(mLike != null) mLike.setText(popularity + "");
        if(mMsg != null) mMsg.setText(comment + "");
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_like_menu:
                if (isLike) showLike();
                else caceLike();
                isLike = !isLike;
                break;
            case R.id.tv_msg_menu:
                goToCommentActivity();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "http://news-at.zhihu.com/api/4/news/" + mId;
        ShareUtil.shareEssay(this,url);
        return true;
    }


    private void caceLike() {
        //动态改变TextView所显示的图片
        mLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.praise, 0, 0, 0);
        mLike.setText((popularity = popularity - 1) + "");
    }

    public void showLike() {
        mLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.praised, 0, 0, 0);
        mLike.setText((popularity = popularity + 1) + "");
        ToastUtil.showImageToast(popularity);
    }

    private void goToCommentActivity() {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(Constants.ID, mId);
        intent.putExtra(Constants.NUM, comment);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //出栈
            pop();
        } else {
            finishAfterTransition();
        }
    }

}
