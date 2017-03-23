package com.dom.red.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.dom.red.app.App;
import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.HomeListBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.MainContract;
import com.dom.red.ui.Adapter.HomeTopViewPager;
import com.dom.red.ui.widget.ImageViewAndText;
import com.dom.red.util.PxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by dom4j on 2017/3/8.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    private List<HomeListBean.TopStoriesBean> mTopList;
    private ArrayList<View> mList;
    private ViewPager vp;
    private HomeTopViewPager adapter;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper){
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getHomeList() {
        Observable<HomeListBean> homeList = mRetrofitHelper.getHomeList();
        homeList.compose(RxHelper.<HomeListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<HomeListBean>(this) {
                    @Override
                    public void onNext(HomeListBean homeListBean) {
                        mTopList = homeListBean.getTop_stories();

                        mView.showHomeList(homeListBean);
                    }
                });
    }

    @Override
    public void getTopViewPager(boolean flag) {
        if(mList == null){
            mList = new ArrayList<>();
        }
        if(adapter == null){
            adapter = new HomeTopViewPager();
        }
        if(flag){
            mList.clear();
            vp = null;
            WindowManager wm = (WindowManager) App.getInstance()
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            vp = new ViewPager(App.getInstance());
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.width = width;
            int px = PxUtil.dp2px(App.getInstance(),220);
            lp.height = px;
            vp.setLayoutParams(lp);
            for(HomeListBean.TopStoriesBean topList : mTopList){
                ImageViewAndText ivt = new ImageViewAndText(App.getInstance());
                ivt.setImageUrl(topList.getImage());
                ivt.setText(topList.getTitle());
                mList.add(ivt);
            }
            adapter.setDataList(mList);
            vp.setAdapter(adapter);
        }
        mView.showTopViewPager(vp);
    }

    @Override
    public void getBeforeList(String date) {
        Observable<HomeListBean> observable = mRetrofitHelper.getBeforeList(date);
        observable.compose(RxHelper.<HomeListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<HomeListBean>(this) {
                    @Override
                    public void onNext(HomeListBean value) {
                        mView.showBeforeList(value);
                    }
                });
    }

}
