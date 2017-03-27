package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.ui.main.adapter.HomeViewPager;

/**
 * Created by dom4j on 2017/3/24.
 */

public interface HomeContract {
    interface View extends BaseView{
        void showHomeList(HomeViewPager adapter);
    }

    interface Presenter extends BasePresenter<View>{
        void createHomeList();
    }
}
