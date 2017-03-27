package com.dom.red.presenter.contract;

import com.dom.red.base.BasePresenter;
import com.dom.red.base.BaseView;
import com.dom.red.model.bean.zhihu.SectionBean;


/**
 * Created by dom4j on 2017/3/25.
 */

public interface ZhiSectionContract {
    interface View extends BaseView {
          void showSectionList(SectionBean list);
    }
    interface Presenter extends BasePresenter<View> {
          void getSectionList();
    }
}
