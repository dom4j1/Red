package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.DetailBean;
import com.dom.red.model.bean.ExtraInfo;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ContentContract;
import com.dom.red.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/10.
 */

public class ContentPresenter extends RxPresenter<ContentContract.View> implements ContentContract.Presenter{

    private RetrofitHelper mRetrofitHelp;
    @Inject
    public ContentPresenter(RetrofitHelper retrofitHelper){
          this.mRetrofitHelp = retrofitHelper;
    }

    @Override
    public void getContent(int id) {
        Observable<DetailBean> detailList = mRetrofitHelp.getDetailList(id);
        detailList.compose(RxHelper.rxSchedulerHelper())
                .subscribe(new Subscribe2Help<DetailBean>(this) {
                    @Override
                    public void onNext(DetailBean value) {
                        mView.showContent(value);
                    }
                });
    }

    @Override
    public void getNewsInfo(int id) {
        Observable<ExtraInfo> edtraInfo = mRetrofitHelp.getNewsInfo(id);
        edtraInfo.compose(RxHelper.rxSchedulerHelper())
                .subscribe(new Subscribe2Help<ExtraInfo>(this) {
                    @Override
                    public void onNext(ExtraInfo extraInfo) {
                         mView.showNewsInfo(extraInfo);
                    }
                });
    }
}
