package com.dom.red.ui.gank.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.dom.red.util.Constants;
import com.dom.red.util.FileUtil;
import com.dom.red.util.ShareUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by dom4j on 2017/3/27.
 */

public class MeiZiActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.iv_meizi)
    ImageView mImageView;

    PhotoViewAttacher mAttacher;
    Bitmap bitmap;
    private String url;

    @Override
    protected int getLayout() {
        return R.layout.activity_meizi;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar,"");
        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.URL);
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bitmap = resource;
                mImageView.setImageBitmap(resource);
                mAttacher = new PhotoViewAttacher(mImageView);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meizi_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.meizi_save:
                saveImageToBitmap();
                break;
            case R.id.meizi_share:
                shareImageToFriend();
                break;
        }
        return true;
    }

    private void shareImageToFriend() {
        ShareUtil.shareImage(this,FileUtil.saveBitmapToFile(url,bitmap,mImageView),getResources().getString(R.string.image_share));
    }

    private void saveImageToBitmap() {
        FileUtil.saveBitmapToFile(url,bitmap,mImageView);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }
}
