package com.dom.red.presenter.contract;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.BeforeBean;
import com.dom.red.model.bean.HomeListBean;

/**
 * Created by dom4j on 2017/3/8.
 */

public interface MainContract {

    interface View extends BaseView{
         //回调主页面数据
        void showHomeList(HomeListBean homeListBean);

        //头部ViewPager
        void showTopViewPager(ViewPager viewPager);

        void showBeforeList(HomeListBean homeListBean);

    }

    interface Presenter extends BasePresenter<View>{
         //获取主页面数据
        void getHomeList();

         //获取头部ViewPager
        void getTopViewPager(boolean flag);

        void getBeforeList(String date);

    }
}
