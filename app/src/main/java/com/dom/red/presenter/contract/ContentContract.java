package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.DetailBean;
import com.dom.red.model.bean.zhihu.ExtraInfo;

/**
 * Created by dom4j on 2017/3/10.
 */

public interface ContentContract {
    interface View extends BaseView{
        void showContent(DetailBean detailBean);
        void showNewsInfo(ExtraInfo extraInfo);
    }
    interface Presenter extends BasePresenter<View>{
         void getContent(int id);
         void getNewsInfo(int id);
    }
}
