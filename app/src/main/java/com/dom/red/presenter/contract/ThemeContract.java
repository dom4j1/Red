package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.ThemeListBean;
import com.dom.red.ui.Adapter.ThemeDownVp;
import com.dom.red.ui.Adapter.ThemeTopViewPager;

import java.util.List;

/**
 * Created by dom4j on 2017/3/23.
 */

public interface ThemeContract {
    interface View extends BaseView{
        void showThemeList(ThemeListBean themeListBean);

        void showTopViewPager(ThemeTopViewPager adapter);

        void showDownViewPager(ThemeDownVp tdv);
    }

    interface Presenter extends BasePresenter<View>{
        void getThemeList();

        void createTopAdapter(List<ThemeListBean.OthersBean> list);

        void createDownAdapter(List<ThemeListBean.OthersBean> list);
    }
}
