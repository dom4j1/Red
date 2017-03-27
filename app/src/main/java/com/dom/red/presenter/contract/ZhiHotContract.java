package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.HotListBean;


/**
 * Created by dom4j on 2017/3/25.
 */

public interface ZhiHotContract {
    interface View extends BaseView {
        void showHotListData(HotListBean data);
    }
    interface Presenter extends BasePresenter<View> {
        void getHotListData();
    }
}
