package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.LongCommentBean;
import com.dom.red.model.bean.ShortCommentBean;

/**
 * Created by dom4j on 2017/3/13.
 */

public interface CommentContract {
    interface View extends BaseView{
        void showLongComment(LongCommentBean longBean);

        void showShortComment(ShortCommentBean shortBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getLongComment(int id);

        void getShortComment(int id);
    }
}
