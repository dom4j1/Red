package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.ThemeList;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ThemeFragmentContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemeFragmentPresenter extends RxPresenter<ThemeFragmentContract.View> implements ThemeFragmentContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemeFragmentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getThemeList(int id) {
        Observable<ThemeList> themeList = mRetrofitHelper.getThemeList(id);
        themeList.compose(RxHelper.<ThemeList>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<ThemeList>(this) {
                    @Override
                    public void onNext(ThemeList value) {
                        mView.showThemeList(value);
                    }
                });
    }
}
