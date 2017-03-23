package com.dom.red.base;

import android.util.Log;

import com.dom.red.util.LogUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dom4j on 2017/3/7.
 * 基于Rx的Presenter封装 更好的控制订阅的声明周期
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T>{

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubScribe(){
        if(mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
        Log.d("TAG","停止所有的请求");
    }

    public void addSubscribe(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        Log.e("TAG","请求入列");
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubScribe();
    }
}
