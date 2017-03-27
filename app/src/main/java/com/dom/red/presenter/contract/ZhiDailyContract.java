package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.HomeListBean;

import java.util.List;


/**
 * Created by dom4j on 2017/3/25.
 */

public interface ZhiDailyContract {
    interface View extends BaseView {
       void showDailyList(HomeListBean data);

        void showBeforeList(HomeListBean data);
    }
    interface Presenter extends BasePresenter<View> {

        void getDailyList();

        void getBeforeList(String date);
    }
}
