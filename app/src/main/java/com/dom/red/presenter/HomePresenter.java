package com.dom.red.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.presenter.contract.HomeContract;
import com.dom.red.ui.main.adapter.HomeViewPager;
import com.dom.red.ui.gank.activity.HomeActivity;
import com.dom.red.ui.gank.fragment.HomeFragment;
import com.dom.red.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dom4j on 2017/3/24.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HomePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void createHomeList() {
        String[] response = new String[]{"Android","iOS","前端"};
        List<Fragment> fragments = new ArrayList<>();
        //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        for(int i = 0 ; i < response.length ; i++){
            HomeFragment fragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.URL, response[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        HomeViewPager adapter = new HomeViewPager(((HomeActivity)mView).getSupportFragmentManager());
        adapter.setDataList(fragments);
        adapter.setTitleList(response);
        mView.showHomeList(adapter);
    }
}
