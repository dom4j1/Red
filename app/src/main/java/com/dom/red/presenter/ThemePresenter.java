package com.dom.red.presenter;

import android.support.v4.app.Fragment;
import android.view.View;

import com.dom.red.app.App;
import com.dom.red.base.RxPresenter;
import com.dom.red.model.bean.zhihu.ThemeListBean;
import com.dom.red.model.http.help.RetrofitHelper;
import com.dom.red.model.http.help.RxHelper;
import com.dom.red.model.http.help.Subscribe2Help;
import com.dom.red.presenter.contract.ThemeContract;
import com.dom.red.ui.main.adapter.ThemeDownVp;
import com.dom.red.ui.main.adapter.ThemeTopViewPager;
import com.dom.red.ui.zhihu.activity.ThemeActivity;
import com.dom.red.ui.zhihu.fragment.ThemeFragment;
import com.dom.red.ui.main.widget.ImageViewAndText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getThemeList() {
        Observable<ThemeListBean> themeList = mRetrofitHelper.getThemeList();
        themeList.compose(RxHelper.<ThemeListBean>rxSchedulerHelper())
                .subscribe(new Subscribe2Help<ThemeListBean>(this) {
                    @Override
                    public void onNext(ThemeListBean value) {
                        mView.showThemeList(value);
                    }
                });
    }

    @Override
    public void createTopAdapter(List<ThemeListBean.OthersBean> list) {
        List<View> viewList = new ArrayList<>();
        for(ThemeListBean.OthersBean bean : list){
            ImageViewAndText ivt = new ImageViewAndText(App.getInstance());
            ivt.setImageUrl(bean.getThumbnail());
           // ivt.setText(bean.getName());
            viewList.add(ivt);
        }
        ThemeTopViewPager adapter = new ThemeTopViewPager();
        adapter.setDataList(viewList);
        mView.showTopViewPager(adapter);
    }

    @Override
    public void createDownAdapter(List<ThemeListBean.OthersBean> list) {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for(ThemeListBean.OthersBean bean : list){
            ThemeFragment tf = new ThemeFragment();
            tf.setId(bean.getId());
            strList.add(bean.getName());
            fragmentList.add(tf);
        }
        ThemeDownVp tdv = new ThemeDownVp(((ThemeActivity)mView).getSupportFragmentManager());
        tdv.setDateList(fragmentList);
        tdv.setStringList(strList);
        mView.showDownViewPager(tdv);
    }
}
