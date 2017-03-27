package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.HomeListBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ZhiDailyContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/25.
 */

public class DailyPresenter extends RxPresenter<ZhiDailyContract.View> implements ZhiDailyContract.Presenter{
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public DailyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getDailyList() {
        Observable<HomeListBean> homeList = mRetrofitHelper.getHomeList();
        homeList.compose(RxHelper.<HomeListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<HomeListBean>(this) {
                    @Override
                    public void onNext(HomeListBean value) {
                        mView.showDailyList(value);
                    }
                });
    }

    @Override
    public void getBeforeList(String date) {
        Observable<HomeListBean> beforeList = mRetrofitHelper.getBeforeList(date);
        beforeList.compose(RxHelper.<HomeListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<HomeListBean>(this) {
                    @Override
                    public void onNext(HomeListBean value) {
                        mView.showBeforeList(value);
                    }
                });
    }
}
