package com.dom.red.model.http.help;

import android.util.Log;

import com.dom.red.base.RxPresenter;
import com.dom.red.ui.zhihu.fragment.ZhihuDaily;
import com.dom.red.util.SnakerbarUtil;
import com.dom.red.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

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
    public void onError(Throwable throwable) {
        if(throwable instanceof ApiExcpetion){
            ToastUtil.showError("服务器异常");
        }else if(throwable instanceof ConnectException){
            ToastUtil.showError("连接超时");
        }else if(throwable instanceof SocketException){
            ToastUtil.showError("连接超时");
        }else if(throwable instanceof HttpException){
            ToastUtil.showError("请检查您的网络连接 稍后重试" + " =￣ω￣=");
            ZhihuDaily.getInstance().onStopDialog(false);
        }
        Log.d("TAG"," exception : " +throwable);
    }
}
