package com.dom.red.ui.zhihu.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.dom.red.util.Constants;
import com.dom.red.util.NewWorkUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by dom4j on 2017/3/23.
 */

public class WebViewActivity extends SimpleActivity {

    @BindView(R.id.Web_toolBar)
    Toolbar mToolBar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressBar1)
    NumberProgressBar mProgressBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "");

        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.URL);

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
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(mProgressBar == null) return;
                if(newProgress==100){
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
        mWebView.loadUrl(url);
    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
