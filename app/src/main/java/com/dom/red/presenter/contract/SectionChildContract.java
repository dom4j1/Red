package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.SectionChildListBean;

/**
 * Created by dom4j on 2017/3/26.
 */

public interface SectionChildContract {
    interface View extends BaseView{
         void showSectionChildList(SectionChildListBean list);
    }
    interface Presenter extends BasePresenter<View>{
         void getSectionChildList(int id);
    }
}
