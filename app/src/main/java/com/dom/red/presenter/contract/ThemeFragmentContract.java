package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.ThemeList;

/**
 * Created by dom4j on 2017/3/23.
 */

public interface ThemeFragmentContract {

    interface View extends BaseView{
        void showThemeList(ThemeList themeList);
    }

    interface Presenter extends BasePresenter<View>{

        void getThemeList(int id);
    }
}
