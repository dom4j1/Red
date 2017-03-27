package com.dom.red.presenter;

import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.LongCommentBean;
import com.dom.red.model.bean.zhihu.ShortCommentBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.CommentContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/13.
 */

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter{
    private RetrofitHelper mRetrofitHelp;
    @Inject
    public CommentPresenter(RetrofitHelper retrofitHelper){
        this.mRetrofitHelp = retrofitHelper;
    }

    @Override
    public void getLongComment(int id) {
        Observable<LongCommentBean> longComment = mRetrofitHelp.getLongComment(id);
        longComment.compose(RxHelper.<LongCommentBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<LongCommentBean>(this) {
                    @Override
                    public void onNext(LongCommentBean longComment) {
                        mView.showLongComment(longComment);
                    }
                });
    }

    @Override
    public void getShortComment(int id) {
        Observable<ShortCommentBean> shortComment = mRetrofitHelp.getShortComment(id);
        shortComment.compose(RxHelper.<ShortCommentBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<ShortCommentBean>(this) {
                    @Override
                    public void onNext(ShortCommentBean shortComment) {
                        mView.showShortComment(shortComment);
                    }
                });
    }
}
