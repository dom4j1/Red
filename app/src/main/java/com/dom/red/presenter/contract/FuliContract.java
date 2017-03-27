package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.gank.MeiziBean;

import java.util.List;

/**
 * Created by dom4j on 2017/3/24.
 */

public interface FuliContract {
    interface View extends BaseView{
        void showMeiziList(List<MeiziBean> list);

    }
    interface Presenter extends BasePresenter<View>{
        void getMeiziList(String type,int num,int page);
    }
}
