package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.HotListBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ZhiHotContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/25.
 */

public class HotPresenter extends RxPresenter<ZhiHotContract.View>implements ZhiHotContract.Presenter{
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HotPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getHotListData() {
        Observable<HotListBean> hotList = mRetrofitHelper.getHotList();
        hotList.compose(RxHelper.<HotListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<HotListBean>(this) {
                    @Override
                    public void onNext(HotListBean value) {
                          mView.showHotListData(value);
                    }
                });
    }
}
