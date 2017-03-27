package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.SectionBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ZhiDailyContract;
import com.dom.red.presenter.contract.ZhiSectionContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/25.
 */

public class SectionPresenter extends RxPresenter<ZhiSectionContract.View> implements ZhiSectionContract.Presenter{
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public SectionPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getSectionList() {
        Observable<SectionBean> sectionList = mRetrofitHelper.getSectionList();
        sectionList.compose(RxHelper.<SectionBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<SectionBean>(this) {
                    @Override
                    public void onNext(SectionBean value) {
                         mView.showSectionList(value);
                    }
                });
    }
}
