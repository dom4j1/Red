package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.SectionChildListBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.SectionChildContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/26.
 */

public class SectionChildPresenter extends RxPresenter<SectionChildContract.View> implements SectionChildContract.Presenter{
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionChildPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getSectionChildList(int id) {
        Observable<SectionChildListBean> sectionChildList = mRetrofitHelper.getSectionChildList(id);
        sectionChildList.compose(RxHelper.<SectionChildListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<SectionChildListBean>(this) {
                    @Override
                    public void onNext(SectionChildListBean value) {
                        mView.showSectionChildList(value);
                    }
                });
    }
}
