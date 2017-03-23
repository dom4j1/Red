package com.dom.red.model.http.help;

import android.util.Log;

import com.dom.red.base.RxPresenter;
import com.dom.red.util.ToastUtil;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dom4j on 2017/3/8.
 */

public abstract class Subscribe2Help<T> implements Observer<T> {

    RxPresenter mPresenter;
    public Subscribe2Help(RxPresenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mPresenter.addSubscribe(d);
    }

    @Override
    public void onComplete() {}

    @Override
    public void onError(Throwable e) {
        Log.e("TAG","e :" + e);
    }
}
