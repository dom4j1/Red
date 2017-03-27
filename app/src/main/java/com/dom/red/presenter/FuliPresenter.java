package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.model.http.response.GankHttpResult;
import com.dom.red.presenter.contract.FuliContract;
import com.dom.red.ui.gank.activity.FuliActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.dom.red.model.http.help.RxHelper.rxSchedulerHelper;

/**
 * Created by dom4j on 2017/3/24.
 */

public class FuliPresenter extends RxPresenter<FuliContract.View> implements FuliContract.Presenter{
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public FuliPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getMeiziList(String type, int num, int page) {
        Observable<GankHttpResult<List<MeiziBean>>> meiziList = mRetrofitHelper.getMeiziList(type, num, page);
        meiziList.compose(RxHelper.<GankHttpResult<List<MeiziBean>>>rxSchedulerHelper())
                .compose(RxHelper.<List<MeiziBean>>handleResult())
                .subscribe(new Subscribe2Help<List<MeiziBean>>(this) {
                    @Override
                    public void onNext(List<MeiziBean> value) {
                        mView.showMeiziList(value);
                    }
                });
    }
}
