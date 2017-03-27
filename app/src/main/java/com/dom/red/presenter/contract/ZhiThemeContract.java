package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;


/**
 * Created by dom4j on 2017/3/25.
 */

public interface ZhiThemeContract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<View> {
    }
}
