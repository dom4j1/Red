package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.gank.ClassifyList;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.model.http.response.GankHttpResult;
import com.dom.red.presenter.contract.HomeFragmentContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by dom4j on 2017/3/24.
 */

public class HomeFragmentPresenter extends RxPresenter<HomeFragmentContract.View> implements HomeFragmentContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HomeFragmentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getDataList(String type, int num, int page) {
        Observable<GankHttpResult<List<ClassifyList>>> classList = mRetrofitHelper.getClassList(type, num, page);
        classList.compose(RxHelper.<GankHttpResult<List<ClassifyList>>>rxSchedulerHelper())
                .compose(RxHelper.<List<ClassifyList>>handleResult())
                .subscribe(new Subscribe2Help<List<ClassifyList>>(this) {
                    @Override
                    public void onNext(List<ClassifyList> value) {
                        mView.showDataList(value);
                    }
                });
    }

    @Override
    public void getGirlList(int num) {
        Observable<GankHttpResult<List<MeiziBean>>> girlList = mRetrofitHelper.getMeiziList("福利", num, 1);
        girlList.compose(RxHelper.<GankHttpResult<List<MeiziBean>>>rxSchedulerHelper())
                .compose(RxHelper.<List<MeiziBean>>handleResult())
                .subscribe(new Subscribe2Help<List<MeiziBean>>(this) {
                    @Override
                    public void onNext(List<MeiziBean> value) {
                        mView.showGrilList(value);
                    }
                });
    }
}
