package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.gank.ClassifyList;
import com.dom.red.model.bean.gank.MeiziBean;

import java.util.List;

/**
 * Created by dom4j on 2017/3/24.
 */

public interface HomeFragmentContract {

    interface View extends BaseView{
         void showDataList(List<ClassifyList> list);

        void showGrilList(List<MeiziBean> data);
    }
    interface Presenter extends BasePresenter<View>{
        void getDataList(String type,int num,int page);

        void getGirlList(int num);
    }
}
